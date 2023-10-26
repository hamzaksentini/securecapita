package hk.brothers.securecapita.repository.implementation;

import hk.brothers.securecapita.domain.User;
import hk.brothers.securecapita.exception.ApiException;
import hk.brothers.securecapita.repository.RoleRepository;
import hk.brothers.securecapita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

import static hk.brothers.securecapita.enumeration.RoleType.ROLE_USER;
import static hk.brothers.securecapita.query.UserQuery.*;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImplementation implements UserRepository<User> {


    private final NamedParameterJdbcTemplate jdbc;
    private RoleRepository roleRepository;
    @Override
    public User create(User user) {
        // check email is unique
        if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0) throw new ApiException("Email already in use");
        // save new user
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters,holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
            roleRepository.addRoleToUser(user.getId(),ROLE_USER.name());
        } catch (EmptyResultDataAccessException exception){
    } catch (Exception e) {
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
        return null;
    }
}
