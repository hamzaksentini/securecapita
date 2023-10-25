package hk.brothers.securecapita.repository;

import hk.brothers.securecapita.domain.User;

import java.util.Collection;

public interface UserRepository <T extends User> {
    T create (T user);
    Collection<T> list(int page, int pagesize);
    T get (Long id);
    T update (T data);
    Boolean delete(Long id);


}

