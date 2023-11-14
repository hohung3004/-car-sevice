package com.notfound.carservice.repository;


import com.notfound.carservice.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    @Query("select w from Wishlist w where w.product.id = :idProduct and w.user.id = :idUser")
    Wishlist findWhishlistByCreate(Integer idProduct, Integer idUser);

    @Query("select w from Wishlist w where w.id <> :id ")
    Wishlist findWishlistByUpdate(Integer id);
}
