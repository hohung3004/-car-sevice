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

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name ="meeting_day")
    private LocalDateTime meetingDay;

    @Column(name ="product_price")
    private double productPrice;

    @Column(name ="discount_rate")
    private float discountRate;

    @Column(name ="status")
    private String status;

    @Column(name ="create_at")
    private LocalDateTime createAt;

    @Column(name ="update_at")
    private LocalDateTime updateAt;

    @Column(name ="delete_at")
    private LocalDateTime deleteAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonIgnore
    private Brand brand;

    @OneToMany (mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Cart> carts;

    @OneToMany (mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Image> image;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(
            name = "product_showroom",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "showroom_id"))
    private Set<Showroom> showroom;

    public Product(Integer idProduct) {
        this.id = idProduct;
    }
}
