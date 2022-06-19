package com.sh.hexagonal.account.domain;

import com.sh.hexagonal.common.crypto.Crypto;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class RegNoConverter implements AttributeConverter<String, String> {
    private final Crypto crypto;

    public RegNoConverter(Crypto crypto) {
        this.crypto = crypto;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if(StringUtils.hasText(attribute)) {
            return crypto.encrypt(attribute);
        }
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if(StringUtils.hasText(dbData)) {
            return crypto.decrypt(dbData);
        }
        return dbData;
    }
}
