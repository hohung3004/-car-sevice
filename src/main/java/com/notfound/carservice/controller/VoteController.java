package com.notfound.carservice.controller;

import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateVoteRequest;
import com.notfound.carservice.model.request.UpdateVoteRequest;
import com.notfound.carservice.model.response.CreateVoteResponse;
import com.notfound.carservice.model.response.GetVoteResponse;
import com.notfound.carservice.model.response.UpdateVoteResponse;
import com.notfound.carservice.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    @Autowired
    VoteService voteService;

    @PostMapping("/create")
    public BaseResponseApi<CreateVoteResponse> create(@Valid @RequestBody CreateVoteRequest request) {
        BaseResponseApi<CreateVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        // call service
        try {
            responseApi = voteService.createVote(request);
            return responseApi;
        }
        catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi. setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @PostMapping("/update")
    public BaseResponseApi<UpdateVoteResponse> update (@Valid @RequestBody UpdateVoteRequest request){
        BaseResponseApi<UpdateVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {
            responseApi = voteService.updateVote(request);
            return responseApi;
        }catch (Exception e ){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/get-all")
    public BaseResponseApi<List<GetVoteResponse>> getAllVote() {
        BaseResponseApi<List<GetVoteResponse>>responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = voteService.getAllVote();
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @GetMapping("/{id}")
    public BaseResponseApi<GetVoteResponse> findVoteById(@PathVariable Integer id){
        BaseResponseApi<GetVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = voteService.findById(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @DeleteMapping("/{id}")
    public BaseResponseApi<GetVoteResponse> deleteById(@PathVariable Integer id){
        BaseResponseApi<GetVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            responseApi = voteService.deleteFindId(id);
            return responseApi;
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
}
