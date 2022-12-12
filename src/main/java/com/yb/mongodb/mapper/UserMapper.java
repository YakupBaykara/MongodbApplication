package com.yb.mongodb.mapper;

import com.yb.mongodb.model.User;
import com.yb.mongodb.model.dto.UserDTO;


public interface UserMapper {

    User mapToEntity(UserDTO userDTO, User user);

    UserDTO mapToDTO(User user, UserDTO userDTO);
}
