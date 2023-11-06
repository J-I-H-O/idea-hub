package com.jbnu.ideahub.member.dto.request;

import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberCreateUpdateRequest {

    private static final int MAX_STUDENT_ID_SIZE = 50;
    private static final int MAX_PASSWORD_SIZE = 50;
    private static final int MAX_NAME_SIZE = 20;
    private static final int MAX_STATUS_SIZE = 20;
    private static final int MAX_ROLE_SIZE = 20;
    private static final String STUDENT_ID_BLANK_ERROR_MESSAGE = "학번은 공백일 수 없습니다.";
    private static final String STUDENT_ID_LENGTH_ERROR_MESSAGE = "학번은 " + MAX_STUDENT_ID_SIZE + "자를 넘을 수 없습니다.";
    private static final String PASSWORD_BLANK_ERROR_MESSAGE = "비밀번호는 공백일 수 없습니다.";
    private static final String PASSWORD_LENGTH_ERROR_MESSAGE = "비밀번호는 " + MAX_PASSWORD_SIZE + "자를 넘을 수 없습니다.";
    private static final String NAME_BLANK_ERROR_MESSAGE = "이름은 공백일 수 없습니다.";
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 " + MAX_NAME_SIZE + "자를 넘을 수 없습니다.";
    private static final String EMAIL_BLANK_ERROR_MESSAGE = "이메일은 공백일 수 없습니다.";
    private static final String STATUS_BLANK_ERROR_MESSAGE = "회원 상태는 공백일 수 없습니다.";
    private static final String STATUS_LENGTH_ERROR_MESSAGE = "회원 상태는 " + MAX_STATUS_SIZE + "자를 넘을 수 없습니다.";
    private static final String ROLE_BLANK_ERROR_MESSAGE = "회원 권한은 공백일 수 없습니다.";
    private static final String ROLE_LENGTH_ERROR_MESSAGE = "회원 권한은 " + MAX_ROLE_SIZE + "자를 넘을 수 없습니다.";

    @NotBlank(message = STUDENT_ID_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_STUDENT_ID_SIZE, message = STUDENT_ID_LENGTH_ERROR_MESSAGE)
    private final String studentId;

    // TODO: 비밀번호 검증 (@Pattern)
    @NotBlank(message = PASSWORD_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_PASSWORD_SIZE, message = PASSWORD_LENGTH_ERROR_MESSAGE)
    private final String password;

    @NotBlank(message = NAME_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_NAME_SIZE, message = NAME_LENGTH_ERROR_MESSAGE)
    private final String name;

    // TODO: 이메일 검증 (@Pattern)
    @NotBlank(message = EMAIL_BLANK_ERROR_MESSAGE)
    private final String email;

    private final String github;

    @NotBlank(message = STATUS_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_STATUS_SIZE, message = STATUS_LENGTH_ERROR_MESSAGE)
    private final MemberStatus status;

    @NotBlank(message = ROLE_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_ROLE_SIZE, message = ROLE_LENGTH_ERROR_MESSAGE)
    private final MemberRole role;
}
