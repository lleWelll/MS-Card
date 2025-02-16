package org.example.mscard.service.impl;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import org.example.mscard.service.EncryptionService;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    private static final String SECRET_KEY = "mySecretKey123456"; // ДОЛЖНО ХРАНИТЬСЯ В НАСТРОЙКАХ
    private static final String SALT = "12345678"; // ДОЛЖНО БЫТЬ УНИКАЛЬНЫМ ДЛЯ КАЖДОГО ПРОЕКТА

    private final TextEncryptor encryptor = Encryptors.text(SECRET_KEY, SALT);

    public String encrypt(String data) {
        return encryptor.encrypt(data);
    }

    public String decrypt(String encryptedData) {
        return encryptor.decrypt(encryptedData);
    }
}
