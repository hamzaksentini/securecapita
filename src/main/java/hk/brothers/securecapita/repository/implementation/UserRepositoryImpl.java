package hk.brothers.securecapita.repository.implementation;

import hk.brothers.securecapita.domain.User;
import hk.brothers.securecapita.exception.ApiException;
import hk.brothers.securecapita.repository.RoleRepository;
import hk.brothers.securecapita.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static hk.brothers.securecapita.enumeration.RoleType.ROLE_USER;
import static hk.brothers.securecapita.enumeration.VerificationType.ACCOUNT;
import static hk.brothers.securecapita.query.UserQuery.*;
import static java.util.Objects.requireNonNull;

@Repository
@AllArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {


    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create(User user) {
        // check email is unique
        if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0) throw new ApiException("Email already in use");
        // save new user
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
            // Add role to user
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());
            // Send verification URL
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            // Save URL in verification table
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY,Map.of("userId", user.getId(),"url", verificationUrl));
            //mssing the email sender part
            user.setEnabled(false);
            user.setNotLocked(true);
            return user;
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No Role found by name: " + ROLE_USER.name());

        } catch (Exception e) {
            throw new ApiException("An error occurred please try again.");
        }

    }

    @Override
    public Collection<User> list(int page, int pagesize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(User user) {

        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }

    private String getVerificationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "*" + key).toUriString();
    }
}
