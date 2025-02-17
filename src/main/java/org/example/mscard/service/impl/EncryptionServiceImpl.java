package org.example.mscard.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import org.example.mscard.service.EncryptionService;

@Service
@PropertySource("classpath:secret.properties")
public class EncryptionServiceImpl implements EncryptionService {

    private final TextEncryptor encryptor;

    public EncryptionServiceImpl(@Value("${encryption.key}") String SECRET_KEY, @Value("${encryption.salt}") String SALT) {
        this.encryptor = Encryptors.text(SECRET_KEY, SALT);
    }

    public String encrypt(String data) {
        return encryptor.encrypt(data);
    }

    public String decrypt(String encryptedData) {
        return encryptor.decrypt(encryptedData);
    }
}
