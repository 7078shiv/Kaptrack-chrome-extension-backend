package com.example.demo.service;

import com.example.demo.dto.UserDataDto;
import com.example.demo.modal.User;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Base64;
@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepo repo;

    public ResponseEntity<?> saveUserOrUpdate(UserDataDto dto) {
        boolean ans = false;
        User user = new User();
        try {
            user.setUserName(dto.getUserName());
            Base64.Encoder encoder = Base64.getEncoder();
            //String originalString = "YOUR_SECRETE_KEY";
            String encodedPassword = encoder.encodeToString(dto.getPassword().getBytes());

            System.out.println("Encrypted Value :: " + encodedPassword);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(encodedPassword);
            System.out.println("Decrypted Value :: " + new String(bytes));
            user.setName(dto.getName());
            user.setPassword(encodedPassword);

            ResponseEntity<?> findUser= findUserByUserNameAndPassword(dto);
            user.setId(dto.getId());
            ans = repo.saveOrUpdate(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ans ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>("Not Save Or Update", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteUserByUserNameAndPassword(UserDataDto dto) {
        boolean res = false;
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedPassword = encoder.encodeToString(dto.getPassword().getBytes());
            dto.setPassword(encodedPassword);
            res = repo.deleteUser(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res ? new ResponseEntity<>("user Deleted Successfully", HttpStatus.OK) : new ResponseEntity<>("user Not found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> findUserByUserNameAndPassword(UserDataDto dto) {
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedPassword = encoder.encodeToString(dto.getPassword().getBytes());
            User user=repo.findByUserNameAndPassword(dto.getUserName(), encodedPassword);
            if(user!=null){
                dto.setId(1L);
            }
            else{
                dto.setId(5L);
            }
            return user!=null? new ResponseEntity<>(user, HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
