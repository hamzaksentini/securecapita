package hk.brothers.securecapita.repository;

import hk.brothers.securecapita.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface RoleRepository  <T extends Role>{

    T create (T role);
    Collection<T> list(int page, int pagesize);
    T get (Long id);
    T update (T role);
    Boolean delete(Long id);


    void addRoleToUser(Long id, String roleName);
}
