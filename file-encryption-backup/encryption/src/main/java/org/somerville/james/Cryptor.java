package org.somerville.james;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import org.apache.commons.lang.NullArgumentException;

/**
 * Created by jsomerville on 7/4/14.
 */
public class Cryptor {
    private String _hexEncodedSalt = null;
    private BytesEncryptor _bytesEncryptor;

    public String getSalt() {
        if (_hexEncodedSalt == null)
            _hexEncodedSalt = KeyGenerators.string().generateKey();
        return _hexEncodedSalt;
    }

    public Cryptor(String password) {
        if (password == null)
            throw new NullArgumentException("Password must not be null.");
        _bytesEncryptor = Encryptors.standard(password, getSalt());
    }

    public byte[] Encrypt(byte[] bytesToEncrypt) {
        return _bytesEncryptor.encrypt(bytesToEncrypt);
    }

    public byte[] Decrypt(byte[] bytesToDencrypt) {
        return _bytesEncryptor.decrypt(bytesToDencrypt);
    }
}