package com.epam.kinorating.model.database.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA256Hasher implements Hasher {
    @Override
    public String hash(String originalString) {
        return DigestUtils.sha256Hex(originalString);
    }
}
