package com.notfound.carservice.domain;

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
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name ="discount_Rate")
    private Float discountRate;

    @Column(name ="status_promotion")
    private String statusPromotion;

    @Column(name ="promotion_code")
    private String promotionCode;

    @Column(name ="create_at")
    private LocalDateTime createAt;

    @Column(name ="update_at")
    private LocalDateTime updateAt;

    @Column(name ="delete_at")
    private LocalDateTime deleteAt;

    @OneToMany (mappedBy = "promo")
    private Set<Order> order;

    public Promo(Integer idPromo) {
        this.id = idPromo;
    }
}
