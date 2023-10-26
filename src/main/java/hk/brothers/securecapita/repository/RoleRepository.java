package hk.brothers.securecapita.repository;

import hk.brothers.securecapita.domain.Role;

import java.util.Collection;

public interface RoleRepository  <T extends Role>{

    T create (T role);
    Collection<T> list(int page, int pagesize);
    T get (Long id);
    T update (T user);
    Boolean delete(Long id);


    void addRoleToUser(Long id, String name);
}
