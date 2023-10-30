package hk.brothers.securecapita.repository.implementation;

import hk.brothers.securecapita.domain.Role;
import hk.brothers.securecapita.exception.ApiException;
import hk.brothers.securecapita.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@AllArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Role create(Role role) {
        try {

        }
    catch (EmptyResultDataAccessException exception) {
        throw new ApiException(exception);

    } catch (Exception e) {
        throw new ApiException("An error occurred please try again.");
    }

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
    public void addRoleToUser(Long id, String roleName) {
        log.info("Adding role {} to user id {}", roleName, id);
        try {

        }
        catch (EmptyResultDataAccessException exception) {
            throw new ApiException(exception);

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
