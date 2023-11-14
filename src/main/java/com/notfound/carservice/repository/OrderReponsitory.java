package com.notfound.carservice.repository;

import com.notfound.carservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.user.id = :idUser and o.promo.id = :idPromo")
    Order findOrderByCreate(Integer idUser, Integer idPromo);

    @Query("select o from Order o where o.id <> :id ")
    Order findByOrderByUpdate(Integer id);
}
