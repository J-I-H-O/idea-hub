package com.jbnu.ideahub.member.dto.request;

import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequest {

    private static final String STUDENT_ID_NULL_ERROR_MESSAGE = "학번은 NULL일 수 없습니다.";
    private static final String STUDENT_ID_BLANK_ERROR_MESSAGE = "학번은 공백일 수 없습니다.";
    private static final String STUDENT_ID_LENGTH_ERROR_MESSAGE = "학번은 50자를 넘을 수 없습니다.";
    private static final String PASSWORD_NULL_ERROR_MESSAGE = "비밀번호는 NULL일 수 없습니다.";
    private static final String PASSWORD_BLANK_ERROR_MESSAGE = "비밀번호는 공백일 수 없습니다.";
    private static final String PASSWORD_LENGTH_ERROR_MESSAGE = "비밀번호는 50자를 넘을 수 없습니다.";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{1,49}$";
    private static final String PASSWORD_FORMAT_ERROR_MESSAGE = "비밀번호는 알파벳과 숫자를 최소 한개씩 포함한 50자 이내의 문자열이어야 합니다.";
    private static final String NAME_NULL_ERROR_MESSAGE = "이름은 NULL일 수 없습니다.";
    private static final String NAME_BLANK_ERROR_MESSAGE = "이름은 공백일 수 없습니다.";
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 20자를 넘을 수 없습니다.";
    private static final String EMAIL_NULL_ERROR_MESSAGE = "이메일은 NULL일 수 없습니다.";
    private static final String EMAIL_BLANK_ERROR_MESSAGE = "이메일은 공백일 수 없습니다.";
    private static final String STATUS_NULL_ERROR_MESSAGE = "회원 상태는 NULL일 수 없습니다.";
    private static final String STATUS_LENGTH_ERROR_MESSAGE = "회원 상태는 20자를 넘을 수 없습니다.";
    private static final String ROLE_NULL_ERROR_MESSAGE = "회원 권한은 NULL일 수 없습니다.";
    private static final String ROLE_LENGTH_ERROR_MESSAGE = "회원 권한은 20자를 넘을 수 없습니다.";

    @NotNull(message = STUDENT_ID_BLANK_ERROR_MESSAGE)
    @NotBlank(message = STUDENT_ID_BLANK_ERROR_MESSAGE)
    @Size(max = 50, message = STUDENT_ID_LENGTH_ERROR_MESSAGE)
    private String studentId;

    @NotNull(message = PASSWORD_NULL_ERROR_MESSAGE)
    @NotBlank(message = PASSWORD_BLANK_ERROR_MESSAGE)
    @Size(max = 50, message = PASSWORD_LENGTH_ERROR_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_FORMAT_ERROR_MESSAGE)
    private String password;

    @NotNull(message = NAME_NULL_ERROR_MESSAGE)
    @NotBlank(message = NAME_BLANK_ERROR_MESSAGE)
    @Size(max = 20, message = NAME_LENGTH_ERROR_MESSAGE)
    private String name;

    @NotNull(message = EMAIL_NULL_ERROR_MESSAGE)
    @NotBlank(message = EMAIL_BLANK_ERROR_MESSAGE)
    @Email
    private String email;

    private String github;

    @NotNull(message = STATUS_NULL_ERROR_MESSAGE)
    private MemberStatus status;

    @NotNull(message = ROLE_NULL_ERROR_MESSAGE)
    private MemberRole role;
}
