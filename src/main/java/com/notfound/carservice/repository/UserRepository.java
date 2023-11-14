package com.notfound.carservice.repository;

import com.notfound.carservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email and u.password = :password and u.status = '1' and u.deleteAt is null")
    User login(String email, String password);

    @Query("select u from User u where u.email = :email")
    User findUserByEmail(String email);
}
