package com.sh.hexagonal.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."), // 장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    ACCOUNT_SIGN_UP_DENIED("가입할수 없는 계정입니다."),
    ACCOUNT_NOT_FOUND("계정을 찾을수 없습니다."),
    ACCOUNT_PASSWORD_MISS_MATCHED("비밀번호가 일치하지 않습니다."),
    ACCOUNT_USER_ID_DUPLICATE("유저ID가 중복입니다."),

    REFUND_CODE_TEST_ERROR("코드테스트 서버에서 에러가 발생했습니다."),
    REFUND_USER_INCOME_NOT_FOUND("소득정보가 없습니다."),

    AUTH_INVALID_TOKEN("토큰이 유효하지 않습니다."),
    AUTH_TOKEN_EXPIRED("토큰이 만료됬습니다.");


    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
