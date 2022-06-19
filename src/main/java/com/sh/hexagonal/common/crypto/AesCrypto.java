package com.sh.hexagonal.common.crypto;

import com.sh.hexagonal.common.exception.BaseException;
import com.sh.hexagonal.common.response.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public final class AesCrypto implements Crypto{
    private final Base64.Encoder base64Encoder;
    private final Base64.Decoder base64Decoder;
    private final SecretKeySpec secretKeySpec;
    private final IvParameterSpec ivParameterSpec;
    private final Charset standardCharset = StandardCharsets.UTF_8;

    public AesCrypto(@Value("${app.crypto.key}") String cryptoKey) {
        this.base64Encoder = Base64.getEncoder();
        this.base64Decoder = Base64.getDecoder();
        this.secretKeySpec = new SecretKeySpec(cryptoKey.getBytes(standardCharset), "AES");
        this.ivParameterSpec = new IvParameterSpec(cryptoKey.substring(0, 16).getBytes(standardCharset));
    }

    @Override
    public String encrypt(String plainText) {
        if(ObjectUtils.isEmpty(plainText)) {
            return null;
        }
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            return base64Encoder.encodeToString(cipher.doFinal(plainText.getBytes(standardCharset)));
        } catch (Exception e) {
            throw new BaseException(ErrorCode.COMMON_SYSTEM_ERROR);
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        if(ObjectUtils.isEmpty(encryptedText)) {
            return encryptedText;
        }
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            var decodeByte = base64Decoder.decode(encryptedText.getBytes(standardCharset));
            return new String(cipher.doFinal(decodeByte));
        } catch (Exception e) {
            throw new BaseException(ErrorCode.COMMON_SYSTEM_ERROR);
        }
    }
}
