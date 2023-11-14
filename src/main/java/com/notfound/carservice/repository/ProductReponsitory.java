package com.notfound.carservice.repository;

import com.notfound.carservice.domain.Product;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReponsitory  extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.productName = :productName")
    Product findByProductByCreate(String productName);

    @Query("select p from Product p where p.id <> :id and p.productName = :productName")
    Product findByProductByUpdate(Integer id, String productName);

}
