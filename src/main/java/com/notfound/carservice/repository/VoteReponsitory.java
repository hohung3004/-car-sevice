package com.notfound.carservice.repository;

import com.notfound.carservice.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoteReponsitory extends JpaRepository<Vote, Integer> {
    @Query("select v from Vote v where v.comment= :comment")
    Vote findVoteByCreate(String comment);

    @Query("select v from Vote v where v.id <> :id and v.comment= :comment")
    default Vote findVoteByUpdate(String comment, Integer id) {
        return null;
    }

    @Query("select v from Vote v where v.id= :id")
    Optional<Vote> findByIdDelete (Integer id);
}