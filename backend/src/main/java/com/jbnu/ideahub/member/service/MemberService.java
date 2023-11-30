package com.jbnu.ideahub.member.service;

import com.jbnu.ideahub.member.dto.request.MemberCreateRequest;
import com.jbnu.ideahub.member.dto.request.MemberUpdateRequest;
import com.jbnu.ideahub.member.dto.response.MemberResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Long save(MemberCreateRequest memberCreateRequest) {
        return null;
    }

    public List<MemberResponse> findAll() {
        return null;
    }

    public MemberResponse findById(Long memberId) {
        return null;
    }

    public void update(Long memberId, MemberUpdateRequest memberUpdateRequest) {

    }

    public void delete(Long memberId) {

    }
}
