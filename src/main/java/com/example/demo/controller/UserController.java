package com.example.demo.controller;

import com.example.demo.dto.UserDataDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveUser(@RequestBody UserDataDto dto){
        return userService.saveUserOrUpdate(dto);
    }

    @DeleteMapping("/delete-by-username-and-password")
    public ResponseEntity<?> deleteUser(@RequestBody UserDataDto dto){
        return userService.deleteUserByUserNameAndPassword(dto);
    }

    @PostMapping("/findByUsernameAndPassword")
    public ResponseEntity<?> findUserBYUsernameAndPassword(@RequestBody UserDataDto dto){
        return userService.findUserByUserNameAndPassword(dto);
    }

}
