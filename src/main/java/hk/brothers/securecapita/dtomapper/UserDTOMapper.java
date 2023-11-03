package hk.brothers.securecapita.dtomapper;

import hk.brothers.securecapita.domain.User;
import hk.brothers.securecapita.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public static User fromDTO(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }

}
