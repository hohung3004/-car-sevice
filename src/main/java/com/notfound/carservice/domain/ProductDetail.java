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
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat_count")
    private Integer seatCount;

    @Column(name = "gear_box")
    private String gearBox;

    @Column(name = "rear_torage")
    private String rearTorage;

    @Column(name ="fuel")
    private String fuel;

    @Column(name ="door_quantity")
    private Integer doorQuantity;

    @Column(name ="color")
    private String color;

    @Column(name ="description")
    private String description;

    @Column(name ="status")
    private String status;

    @Column(name ="create_at")
    private LocalDateTime createAt;

    @Column(name ="update_at")
    private LocalDateTime updateAt;

    @Column(name ="delete_at")
    private LocalDateTime deleteAt;

    @OneToOne
    @MapsId
    @JoinColumn(name ="product_id")
    private Product product;
}
