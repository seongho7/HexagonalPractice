package com.sh.hexagonal.auth.application.service;

import com.sh.hexagonal.account.application.service.AccountPersistenceAdapterSpy;
import com.sh.hexagonal.account.domain.Account;
import com.sh.hexagonal.auth.application.port.in.AccountNotFoundException;
import com.sh.hexagonal.auth.application.port.in.PasswordMissMatchedException;
import com.sh.hexagonal.auth.application.port.in.SignInCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SignInServiceTest {

    @TestFactory
    @DisplayName("회원인증 유스케이스")
    Collection<DynamicTest> signup_use_case() {
        var persistenceAdapterSpy = new AccountPersistenceAdapterSpy();
        var accessTokenPersistenceAdapter = new AccessTokenPersistenceAdapterSpy();
        var loadAccountPort = persistenceAdapterSpy;
        var passwordEncoder = new BCryptPasswordEncoder();
        var tokenProvider = new TokenProviderSpy();

        var signInService = new SignInService(loadAccountPort, passwordEncoder, tokenProvider, accessTokenPersistenceAdapter);

        var command = new SignInCommand("userId", "1234");
        persistenceAdapterSpy.save(new Account(1L, command.getUserId(), passwordEncoder.encode(command.getPassword()), "이름", "1234"));


        return Arrays.asList(
                DynamicTest.dynamicTest("유저 아이디가 없을때 AccountNotFoundException 이 발생한다.", ()->{
                    assertThatExceptionOfType(AccountNotFoundException.class)
                            .isThrownBy(()-> signInService.signIn(new SignInCommand("dummyId", "dummyPasswod")));
                }),

                DynamicTest.dynamicTest("패스 워드가 일치하지 않을때 PasswordMissMatchedException 이 발생한다.", ()->{
                    assertThatExceptionOfType(PasswordMissMatchedException.class)
                            .isThrownBy(()-> signInService.signIn(new SignInCommand(command.getUserId(), "dummyPasswod")));
                }),

                DynamicTest.dynamicTest("인증이 완료된다.", ()->{
                    var accessToken = signInService.signIn(command);
                    var expectToken =  accessTokenPersistenceAdapter.loadById(accessToken.getId());
                    assertEquals(expectToken.get().getToken(), accessToken.getToken());
                })
        );
    }
}