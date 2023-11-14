package com.notfound.carservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name= "comment")
    private String comment;

    @Column(name ="star_raiting")
    private Integer starRaiting;

    @Column(name ="status")
    private String status;

    @Column(name ="create_at")
    private LocalDateTime createAt;

    @Column(name ="update_at")
    private LocalDateTime updateAt;

    @Column(name ="delete_at")
    private LocalDateTime deleteAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}
