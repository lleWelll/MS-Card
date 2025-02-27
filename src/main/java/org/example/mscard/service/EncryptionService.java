package org.example.mscard.service;

public interface EncryptionService {

    public String encrypt(String data);

    public String decrypt(String encryptedData);

}
