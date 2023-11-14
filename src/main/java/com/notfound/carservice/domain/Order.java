package com.notfound.carservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="order_car")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name ="order_date")
    private LocalDateTime orderDate;

    @Column(name ="unit_price")
    private float unitPrice;

    @Column(name ="status")
    private String status;

    @Column(name ="update_at")
    private LocalDateTime updateAt;

    @Column(name ="delete_at")
    private LocalDateTime deleteAt;

    @OneToMany (mappedBy = "order")
    private Set<Cart> carts;

    @ManyToOne
    @JoinColumn(name ="promo_id")
    @JsonIgnore
    private Promo promo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Order(Integer idOrder) {
        this.id = idOrder;
    }
}
