package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class kapTrackDto {
    private String url;
    private String timeTaken;
    private Long userId;
}
