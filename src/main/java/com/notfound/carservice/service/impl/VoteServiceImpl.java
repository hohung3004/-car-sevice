package com.notfound.carservice.service.impl;

import com.notfound.carservice.domain.Promo;
import com.notfound.carservice.domain.Vote;
import com.notfound.carservice.model.adapter.VoteAdapter;
import com.notfound.carservice.model.base.BaseResponseApi;
import com.notfound.carservice.model.base.BaseResponseStatus;
import com.notfound.carservice.model.request.CreateVoteRequest;
import com.notfound.carservice.model.request.UpdatePromoRequest;
import com.notfound.carservice.model.request.UpdateVoteRequest;
import com.notfound.carservice.model.response.CreateVoteResponse;
import com.notfound.carservice.model.response.GetVoteResponse;
import com.notfound.carservice.model.response.UpdatePromoResponse;
import com.notfound.carservice.model.response.UpdateVoteResponse;
import com.notfound.carservice.repository.VoteReponsitory;
import com.notfound.carservice.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteReponsitory voteReponsitory;

    @Autowired
    VoteAdapter voteAdapter;

    @Override
    public BaseResponseApi<CreateVoteResponse> createVote(CreateVoteRequest request) {
        BaseResponseApi<CreateVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();

        try {

            Vote vote = voteReponsitory.save(voteAdapter.createVote(request));
            if (vote == null) {
                responseStatus.setFailed("vote không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(voteAdapter.buildCreateVote(vote));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<UpdateVoteResponse> updateVote (UpdateVoteRequest request) {
        BaseResponseApi<UpdateVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Vote foundVote = voteReponsitory.findVoteByUpdate(request.getComment(), request.getId());
            if (foundVote != null) {
                responseStatus.setFailed("vote không tồn tại!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            Vote vote = voteReponsitory.save(voteAdapter.updateVote(request));
            if (vote == null) {
                responseStatus.setFailed("Cập nhật không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(voteAdapter.buildUpdateVote(vote));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }


    @Override
    public BaseResponseApi<List<GetVoteResponse>> getAllVote() {
        BaseResponseApi<List<GetVoteResponse>> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        List<GetVoteResponse> allVoteResponseList = new ArrayList<>();
        try {
            // jpa getall
            List<Vote> voteList = voteReponsitory.findAll();

            for (Vote vote :
                    voteList) {
                if (vote.getDeleteAt() == null) {
                    allVoteResponseList.add(voteAdapter.buildGetAllVoteResponse(vote));
                }
            }

            responseApi.setData(allVoteResponseList);
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<GetVoteResponse> findById(Integer id) {
        BaseResponseApi<GetVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Vote> vote = voteReponsitory.findByIdDelete(id);
            if (vote.isEmpty()) {
                responseStatus.setNotFound("Không tìm thấy vote theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            responseStatus.setSuccess();
            responseApi.setData(voteAdapter.buildGetAllVoteResponse(vote.get()));
            responseApi.setResponseStatus(responseStatus);
            return responseApi;
        } catch (Exception e) {
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }

    @Override
    public BaseResponseApi<GetVoteResponse> deleteFindId(Integer id) {
        BaseResponseApi<GetVoteResponse> responseApi = new BaseResponseApi<>();
        BaseResponseStatus responseStatus = new BaseResponseStatus();
        try {
            Optional<Vote> vote = voteReponsitory.findByIdDelete(id);
            if (vote.isEmpty()){
                responseStatus.setNotFound("Không tìm thấy vote theo id = " + id);
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }

            vote.get().setDeleteAt(LocalDateTime.now());

            Vote deleteVote = voteReponsitory.save(vote.get());
            if (deleteVote == null) {
                responseStatus.setFailed("Xoá vote không thành công!");
                responseApi.setResponseStatus(responseStatus);
                return responseApi;
            }
            responseApi.setData(voteAdapter.buildGetAllVoteResponse(deleteVote));
            responseStatus.setSuccess();
            responseApi.setResponseStatus(responseStatus);
        }catch (Exception e){
            e.getMessage();
            responseStatus.setException();
            responseApi.setResponseStatus(responseStatus);
        }
        return responseApi;
    }
}
