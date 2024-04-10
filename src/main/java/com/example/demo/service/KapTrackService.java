package com.example.demo.service;
import com.example.demo.dto.kapTrackDto;
import com.example.demo.modal.KapTrackModal;
import com.example.demo.modal.User;
import com.example.demo.repo.KapTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;

@Service
public class KapTrackService {
    @Autowired
    private KapTrackRepo kapTrackRepo;

    public ResponseEntity<?> saveData(kapTrackDto dto) {
        KapTrackModal kapTrackModal=new KapTrackModal();
        boolean ans=false;
        try{
            kapTrackModal.setDate(new Date(System.currentTimeMillis()));
            kapTrackModal.setUrl(dto.getUrl());
            kapTrackModal.setTimeTaken(dto.getTimeTaken());
            User user = new User();
            user.setId(dto.getUserId());
            kapTrackModal.setUser(user);
            ans=kapTrackRepo.saveOrUpdate(kapTrackModal);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ans?new ResponseEntity<>(kapTrackModal,HttpStatus.OK):new ResponseEntity<>("not update Successfully",HttpStatus.BAD_REQUEST);
    }
}
