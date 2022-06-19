package com.sh.hexagonal.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class SelfValidatingTest {

    @Test
    @DisplayName("유효하지 않은 파라미터일때 ConstraintViolationException이 발생하는지 확인한다.")
    void validate_parameter_incorrectly() {
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> new Dummy("", ""));
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> new Dummy(" ", "a@maver.com"));
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> new Dummy("asdfsad", "aaaa"));
    }

    @Test
    @DisplayName("유효한 파라미터일때 정상동작 하는지 확인한다.")
    void validate_parameter_correctly() {
        new Dummy("value", "a@naver.com");
    }

    static class Dummy extends SelfValidating<Dummy> {
        @NotBlank
        private String value;

        @Email
        private String email;

        public Dummy(String value, String email) {
            this.value = value;
            this.email = email;
            this.validateSelf();
        }

        public String getValue() {
            return value;
        }

        public String getEmail() {
            return email;
        }
    }
}