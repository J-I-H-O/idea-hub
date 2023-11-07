package com.jbnu.ideahub.member.service;

import com.jbnu.ideahub.member.dto.request.MemberRequest;
import com.jbnu.ideahub.member.dto.response.MemberResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Long save(MemberRequest memberRequest) {
        return null;
    }

    public List<MemberResponse> findAll() {
        return null;
    }

    public MemberResponse findById(Long memberId) {
        return null;
    }

    public void update(Long memberId, MemberRequest memberRequest) {

    }

    public void delete(Long memberId) {

    }
}
