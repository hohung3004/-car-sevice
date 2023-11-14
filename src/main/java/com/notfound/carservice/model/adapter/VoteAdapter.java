package com.notfound.carservice.model.adapter;


import com.notfound.carservice.domain.User;
import com.notfound.carservice.domain.Vote;
import com.notfound.carservice.model.request.CreateVoteRequest;
import com.notfound.carservice.model.request.UpdateVoteRequest;
import com.notfound.carservice.model.response.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VoteAdapter {
    public Vote createVote (CreateVoteRequest request) {
        return Vote.builder()
                .comment(request.getComment())
                .starRaiting(request.getStarRaiting())
                .status(request.getStatus())
                .createAt(LocalDateTime.now())
                .user(new User(request.getIdUser()))
                .build();
    }
    public Vote updateVote(UpdateVoteRequest request){
        return Vote.builder()
                .id(request.getId())
                .comment(request.getComment())
                .starRaiting(request.getStarRaiting())
                .status(request.getStatus())
                .user(new User(request.getIdUser()))
                .updateAt(LocalDateTime.now())
                .build();
    }

    public CreateVoteResponse buildCreateVote(Vote v){
        return CreateVoteResponse.builder()
                .idUser(v.getUser().getId())
                .comment(v.getComment())
                .starRaiting(v.getStarRaiting())
                .status(v.getStatus())
                .build();
    }

    public UpdateVoteResponse buildUpdateVote(Vote v){
        return UpdateVoteResponse.builder()
                .idUser(v.getUser().getId())
                .comment(v.getComment())
                .starRaiting(v.getStarRaiting())
                .status(v.getStatus())
                .build();
    }

    public GetVoteResponse buildGetAllVoteResponse(Vote vote) {
        return GetVoteResponse.builder()
                .id(vote.getId())
                .comment(vote.getComment())
                .starRaiting(vote.getStarRaiting())
                .status(vote.getStatus())
                .idUser(vote.getUser().getId())
                .build();
    }
}
