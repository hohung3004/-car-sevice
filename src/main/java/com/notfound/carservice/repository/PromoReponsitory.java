package com.notfound.carservice.repository;


import com.notfound.carservice.domain.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromoReponsitory extends JpaRepository<Promo, Integer> {
    @Query("select p from Promo p where p.promotionCode= :promotionCode")
    Promo findPromoByCreate(String promotionCode);

    @Query("select p from Promo p where p.id <> :id and p.promotionCode = :promotionCode")
    Promo findPromoByUpdate(String promotionCode, Integer id);
}
