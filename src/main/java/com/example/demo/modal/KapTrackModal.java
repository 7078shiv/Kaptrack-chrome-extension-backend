package com.example.demo.modal;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name="kap_track_modal")
public class KapTrackModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name="url")
    private String url;

    @Column(name="time_taken")
    private String timeTaken;

    @ManyToOne
    @JoinColumn(name = "user_id") // Name of the foreign key column
    private User user;
}
