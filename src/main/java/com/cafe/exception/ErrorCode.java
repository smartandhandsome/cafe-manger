package com.cafe.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ADMIN
    DUPLICATED_PHONE_NUMBER(400, "중복된 휴대폰 번호입니다."),
    FAILED_LOGIN(404, "이메일 또는 비밀번호를 확인해주세요."),

    // AUTH
    ILLEGAL_TOKEN(401, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),

    // COMMON
    INVALID_INPUT(400, "잘못된 입력 값 입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 에러입니다.");

    private final int code;
    private final String message;
}
