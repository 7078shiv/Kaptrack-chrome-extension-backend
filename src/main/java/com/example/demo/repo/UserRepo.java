package com.example.demo.repo;

import com.example.demo.dto.UserDataDto;
import com.example.demo.modal.User;

public interface UserRepo {
    boolean saveOrUpdate(User dto);

    boolean deleteUser(UserDataDto dto);

    User findByUserNameAndPassword(String username, String encodedPassword);
}
