package com.notfound.carservice.domain;

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
public class Sevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name ="icon")
    private String icon;

    @Column(name ="title")
    private String title;

    @Column(name ="summary")
    private String summary;

    @Column(name ="status")
    private String status;

    @Column(name ="create_at")
    private LocalDateTime createAt;

    @Column(name ="update_at")
    private LocalDateTime updateAt;

    @Column(name ="delete_at")
    private LocalDateTime deleteAt;
}
