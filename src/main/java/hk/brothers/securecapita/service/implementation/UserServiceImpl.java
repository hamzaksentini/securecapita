package hk.brothers.securecapita.service.implementation;

import hk.brothers.securecapita.domain.User;
import hk.brothers.securecapita.dto.UserDTO;
import hk.brothers.securecapita.dtomapper.UserDTOMapper;
import hk.brothers.securecapita.repository.UserRepository;
import hk.brothers.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
