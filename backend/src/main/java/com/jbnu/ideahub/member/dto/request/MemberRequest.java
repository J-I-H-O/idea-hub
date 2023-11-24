package com.jbnu.ideahub.member.dto.request;

import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequest {

    private static final int MAX_STUDENT_ID_SIZE = 50;
    private static final int MAX_PASSWORD_SIZE = 50;
    private static final int MAX_NAME_SIZE = 20;
    private static final int MAX_STATUS_SIZE = 20;
    private static final int MAX_ROLE_SIZE = 20;
    private static final String STUDENT_ID_NULL_ERROR_MESSAGE = "학번은 NULL일 수 없습니다.";
    private static final String STUDENT_ID_BLANK_ERROR_MESSAGE = "학번은 공백일 수 없습니다.";
    private static final String STUDENT_ID_LENGTH_ERROR_MESSAGE = "학번은 " + MAX_STUDENT_ID_SIZE + "자를 넘을 수 없습니다.";
    private static final String PASSWORD_NULL_ERROR_MESSAGE = "비밀번호는 NULL일 수 없습니다.";
    private static final String PASSWORD_BLANK_ERROR_MESSAGE = "비밀번호는 공백일 수 없습니다.";
    private static final String PASSWORD_LENGTH_ERROR_MESSAGE = "비밀번호는 " + MAX_PASSWORD_SIZE + "자를 넘을 수 없습니다.";
    private static final String NAME_NULL_ERROR_MESSAGE = "이름은 NULL일 수 없습니다.";
    private static final String NAME_BLANK_ERROR_MESSAGE = "이름은 공백일 수 없습니다.";
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 " + MAX_NAME_SIZE + "자를 넘을 수 없습니다.";
    private static final String EMAIL_NULL_ERROR_MESSAGE = "이메일은 NULL일 수 없습니다.";
    private static final String EMAIL_BLANK_ERROR_MESSAGE = "이메일은 공백일 수 없습니다.";
    private static final String STATUS_NULL_ERROR_MESSAGE = "회원 상태는 NULL일 수 없습니다.";
    private static final String STATUS_LENGTH_ERROR_MESSAGE = "회원 상태는 " + MAX_STATUS_SIZE + "자를 넘을 수 없습니다.";
    private static final String ROLE_NULL_ERROR_MESSAGE = "회원 권한은 NULL일 수 없습니다.";
    private static final String ROLE_LENGTH_ERROR_MESSAGE = "회원 권한은 " + MAX_ROLE_SIZE + "자를 넘을 수 없습니다.";

    @NotNull(message = STUDENT_ID_BLANK_ERROR_MESSAGE)
    @NotBlank(message = STUDENT_ID_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_STUDENT_ID_SIZE, message = STUDENT_ID_LENGTH_ERROR_MESSAGE)
    private String studentId;

    // TODO: 비밀번호 검증 (@Pattern)
    @NotNull(message = PASSWORD_NULL_ERROR_MESSAGE)
    @NotBlank(message = PASSWORD_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_PASSWORD_SIZE, message = PASSWORD_LENGTH_ERROR_MESSAGE)
    private String password;

    @NotNull(message = NAME_NULL_ERROR_MESSAGE)
    @NotBlank(message = NAME_BLANK_ERROR_MESSAGE)
    @Size(max = MAX_NAME_SIZE, message = NAME_LENGTH_ERROR_MESSAGE)
    private String name;

    // TODO: 이메일 검증 (@Pattern)
    @NotNull(message = EMAIL_NULL_ERROR_MESSAGE)
    @NotBlank(message = EMAIL_BLANK_ERROR_MESSAGE)
    private String email;

    private String github;

    @NotNull(message = STATUS_NULL_ERROR_MESSAGE)
    private MemberStatus status;

    @NotNull(message = ROLE_NULL_ERROR_MESSAGE)
    private MemberRole role;
}
