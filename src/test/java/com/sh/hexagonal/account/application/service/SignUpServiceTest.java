package com.sh.hexagonal.account.application.service;

import com.sh.hexagonal.account.application.port.in.SignUpCommand;
import com.sh.hexagonal.account.application.port.in.exception.AccountSignUpDeniedException;
import com.sh.hexagonal.account.application.port.in.exception.UserIdDuplicateException;
import com.sh.hexagonal.common.id.SequenceGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignUpServiceTest {

    @TestFactory
    @DisplayName("회원가입 유스케이스")
    Collection<DynamicTest> signup_use_case() {
        var persistenceAdapterSpy = new AccountPersistenceAdapterSpy();
        var saveAccountPort = persistenceAdapterSpy;
        var loadAccountPort = persistenceAdapterSpy;
        var passwordEncoder = new BCryptPasswordEncoder();
        var idGenerator = new SequenceGenerator(1);

        var signUpService = new SignUpService(saveAccountPort, loadAccountPort, passwordEncoder, idGenerator);

        var command = SignUpCommand.builder()
                .userId("userId")
                .name("네이버")
                .regNo("901010-1234567")
                .password("1234")
                .build();
        return Arrays.asList(
                DynamicTest.dynamicTest("회원가입에 성공한다.", ()->{
                    signUpService.signUp(command);
                    var oAccount = persistenceAdapterSpy.loadAccountByUserId(command.getUserId());
                    assertTrue(oAccount.isPresent());
                }),

                DynamicTest.dynamicTest("동일한 유저아이디가 있어 UserIdDuplicateException이 발생한다.", ()->{
                    assertThatExceptionOfType(UserIdDuplicateException.class)
                            .isThrownBy(()-> signUpService.signUp(command));
                })

//                DynamicTest.dynamicTest("가입 불가능한 유저명,주민등록번호로 유효성체크시 AccountNotAllowException이 발생하는지 확인한다.", ()->{
//                    var dummyCommand = SignUpCommand.builder()
//                            .userId("userId")
//                            .name("etc")
//                            .regNo("123405")
//                            .password("1234")
//                            .build();
//
//                    assertThatExceptionOfType(AccountSignUpDeniedException.class)
//                            .isThrownBy(()-> signUpService.signUp(dummyCommand));
//                })
        );
    }

}