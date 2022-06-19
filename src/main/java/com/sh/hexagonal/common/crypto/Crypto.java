package com.sh.hexagonal.common.crypto;

public interface Crypto {
    String encrypt(String plainText);
    String decrypt(String encryptedText);
}
