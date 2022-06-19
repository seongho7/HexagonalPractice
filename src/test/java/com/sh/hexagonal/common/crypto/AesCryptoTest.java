package com.sh.hexagonal.common.crypto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AesCryptoTest {
    @DisplayName("암/복호화 테스트")
    @Test
    void crypto() {
        String cryptoKey = "MRbuwc3F5nOxoRS3ZOIib1n4WiDM8qNW";
        String plainText = "1234abcd!@#";
        var aesCrypto = new AesCrypto(cryptoKey);

        String actualValue = aesCrypto.decrypt(aesCrypto.encrypt(plainText));
        assertEquals(plainText, actualValue);
    }
}