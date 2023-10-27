package hk.brothers.securecapita.repository;

import hk.brothers.securecapita.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository <T extends User> {
    T create (T user);
    Collection<T> list(int page, int pagesize);
    T get (Long id);
    T update (T data);
    Boolean delete(Long id);


}

