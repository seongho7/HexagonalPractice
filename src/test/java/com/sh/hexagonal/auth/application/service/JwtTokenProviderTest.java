package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.domain.AccessToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtTokenProviderTest {

    @DisplayName("토큰 생성 테스트")
    @Test
    void createToken() {
        String tokenSecret = "04ca023b39512e46d0c2cf4b48d5aac61d34302994c87ed4eff225dcf3b0a218739f3897051a057f9b846a69ea2927a587044164b7bae5e1306219d50b588cb1";
        long tokenExpirationMillis = 1000000;
        var jwtTokenProvider =new JwtTokenProvider(tokenSecret, tokenExpirationMillis);
        var account = new Account(1L, "userid", "password", "name", "regNo");

        AccessToken accessToken = jwtTokenProvider.createToken(account);
        assertNotNull(accessToken.getToken());
    }
}