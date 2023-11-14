package com.notfound.carservice.service;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.request.CreateVoteRequest;
import com.notfound.carservice.model.request.UpdateVoteRequest;
import com.notfound.carservice.model.response.CreateVoteResponse;
import com.notfound.carservice.model.response.GetVoteResponse;
import com.notfound.carservice.model.response.UpdateVoteResponse;

import java.util.List;

public interface VoteService {
    BaseResponseApi<UpdateVoteResponse> updateVote(UpdateVoteRequest request);
    BaseResponseApi<CreateVoteResponse> createVote(CreateVoteRequest request);
    BaseResponseApi<List<GetVoteResponse>> getAllVote();
    BaseResponseApi<GetVoteResponse> findById(Integer id);
    BaseResponseApi<GetVoteResponse> deleteFindId(Integer id);
}
