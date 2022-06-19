package com.sh.hexagonal.auth.adapter.in.web;

import com.sh.hexagonal.auth.domain.AccessToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccessTokenRes {
    @Schema(description = "엑세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODM...", nullable = false) @NotBlank
    private final String token;

    public AccessTokenRes(AccessToken accessToken) {
        this.token = accessToken.getToken();
    }
}
