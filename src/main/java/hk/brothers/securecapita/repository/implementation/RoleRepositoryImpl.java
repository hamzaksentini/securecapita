package hk.brothers.securecapita.repository.implementation;

import hk.brothers.securecapita.domain.Role;
import hk.brothers.securecapita.exception.ApiException;
import hk.brothers.securecapita.repository.RoleRepository;
import hk.brothers.securecapita.rowmapper.RoleRowMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static hk.brothers.securecapita.enumeration.RoleType.ROLE_USER;
import static hk.brothers.securecapita.query.RoleQuery.*;

@Repository
@AllArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository {
    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public Role create(Role role) {

        return role;
    }


    @Override
    public Collection list(int page, int pagesize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role {} to user id {}", roleName, userId);
        try {
            Role role = (Role) jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName", roleName),new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY,Map.of("userId", userId, "roleId", Objects.requireNonNull(role).getId()));

        }
        catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No Role found by name: " + ROLE_USER.name());

        } catch (Exception e) {
            throw new ApiException("An error occurred please try again.");
        }


    }

    @Override
    public Role getRoleById(Long userId) {
        return null;
    }

    @Override
    public Role getRoleByEmail(String email) {
        return null;
    }

    @Override
    public Void updateUserRole(Long userId, String roleName) {
        return null;
    }
}
