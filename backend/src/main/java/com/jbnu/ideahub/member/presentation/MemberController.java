package com.jbnu.ideahub.member.presentation;

import com.jbnu.ideahub.common.dto.ApiResponse;
import com.jbnu.ideahub.member.dto.request.MemberRequest;
import com.jbnu.ideahub.member.dto.response.MemberResponse;
import com.jbnu.ideahub.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(
            @RequestBody @Valid final MemberRequest memberRequest
    ) {
        final Long memberId = memberService.save(memberRequest);
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberResponse>>> getMembers() {
        final List<MemberResponse> memberResponses = memberService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(memberResponses));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberById(
            @PathVariable final Long memberId
    ) {
        final MemberResponse memberResponse = memberService.findById(memberId);
        return ResponseEntity.ok(new ApiResponse<>(memberResponse));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(
            @PathVariable final Long memberId,
            @RequestBody @Valid final MemberRequest memberRequest
    ) {
        memberService.update(memberId, memberRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{memberId}/delete")
    public ResponseEntity<Void> deleteMember(
            @PathVariable final Long memberId
    ) {
        memberService.delete(memberId);
        return ResponseEntity.noContent().build();
    }
}
