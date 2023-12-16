package edu.miu.cs.cs544.adapter;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.dto.CustomerDTO;
import edu.miu.cs.cs544.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAdapter {
    public UserDTO entityToDTO(User user){
        return new UserDTO(user.getUserName(), user.getUserPass(), user.getActive(), user.getUserType());
    }

    public List<UserDTO> entityToDTOAll(List<User> users){
        return users.stream().map(user -> entityToDTO(user)).collect(Collectors.toList());
    }

    public User DtoToEntity(UserDTO userDTO){
        return new User(userDTO.getUserName(), userDTO.getUserPass(), userDTO.getActive(), userDTO.getUserType());
    }
    public List<User> DtoToEntityAll(List<UserDTO> userDTOs){
        return userDTOs.stream().map(userDTO -> DtoToEntity(userDTO)).collect(Collectors.toList());
    }
}
