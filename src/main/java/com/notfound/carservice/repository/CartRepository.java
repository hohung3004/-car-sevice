package com.notfound.carservice.repository;

import com.notfound.carservice.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("select c from Cart c where c.order.id = :idOrder and c.product.id = :idProduct")
    Cart findCartByCreate(Integer idOrder, Integer idProduct);

    @Query("select c from Cart c where c.id <> :id ")
    Cart findByCartByUpdate(Integer id);
}
