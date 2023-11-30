package com.jbnu.ideahub.member.dto.request;

import com.jbnu.ideahub.member.domain.MemberRole;
import com.jbnu.ideahub.member.domain.MemberStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCreateRequest {

    private static final int MAX_STUDENT_ID_SIZE = 50;
    private static final int MAX_PASSWORD_SIZE = 50;
    private static final int MAX_NAME_SIZE = 50;
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{1,49}$";

    @NotNull(message = "학번은 NULL일 수 없습니다.")
    @NotBlank(message = "학번은 공백일 수 없습니다.")
    @Size(max = 50, message = "학번은 "+ MAX_STUDENT_ID_SIZE + "자를 넘을 수 없습니다.")
    private String studentId;

    @NotNull(message = "비밀번호는 NULL일 수 없습니다.")
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Size(max = 50, message = "비밀번호는 " + MAX_PASSWORD_SIZE + "자를 넘을 수 없습니다.")
    @Pattern(regexp = PASSWORD_PATTERN, message = "비밀번호는 알파벳과 숫자를 최소 한개씩 포함한 " + MAX_PASSWORD_SIZE + "자 이내의 문자열이어야 합니다.")
    private String password;

    @NotNull(message = "이름은 NULL일 수 없습니다.")
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    @Size(max = 20, message = "이름은 " + MAX_NAME_SIZE + "자를 넘을 수 없습니다.")
    private String name;

    @NotNull(message = "이메일은 NULL일 수 없습니다.")
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private String github;

    @NotNull(message = "회원 상태는 NULL일 수 없습니다.")
    private MemberStatus status;

    @NotNull(message = "회원 권한은 NULL일 수 없습니다.")
    private MemberRole role;
}
