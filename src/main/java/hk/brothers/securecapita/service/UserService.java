package hk.brothers.securecapita.service;

import hk.brothers.securecapita.domain.User;
import hk.brothers.securecapita.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
}
