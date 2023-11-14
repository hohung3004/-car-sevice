package com.notfound.carservice.repository;

import com.notfound.carservice.domain.Sevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeviceRepository extends JpaRepository<Sevice, Integer> {

    @Query("select b from Sevice b where b.title= :title")
    Sevice findSeviceByTitle(String title);

    @Query("select b from Sevice b where b.id <> :id and b.title = :title")
    Sevice findSeviceByUpdate(String title, Integer id);

}
