package com.notfound.carservice.repository;

import com.notfound.carservice.domain.Showroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowroomReponsitory extends JpaRepository<Showroom, Integer> {

    @Query("select s from Showroom s where s.showName = :showName")
    Showroom findShowroomByCrate(String showName);

    @Query("select s from Showroom s where s.id <> :id and s.showName = :showName")
    Showroom findShowroomByUpdate(Integer id, String showName);
}
