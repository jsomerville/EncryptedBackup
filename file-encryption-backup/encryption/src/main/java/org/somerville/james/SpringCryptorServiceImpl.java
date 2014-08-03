package org.somerville.james;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.NullArgumentException;

/**
 * Created by jsomerville on 7/4/14.
 */
@Service("cryptorService")
public class SpringCryptorServiceImpl implements CryptorService {
	private String _hexEncodedSalt = null;
    private BytesEncryptor _bytesEncryptor;

    public String getSalt() {
        if (_hexEncodedSalt == null)
            _hexEncodedSalt = KeyGenerators.string().generateKey();
        return _hexEncodedSalt;
    }
    
    @Autowired
    public SpringCryptorServiceImpl(@Value("${encryption.password}") String password) {
    	if (password == null)
            throw new NullArgumentException("Password must not be null.");
        _bytesEncryptor = Encryptors.standard(password, getSalt());
    }

    public byte[] Encrypt(byte[] bytesToEncrypt) {
        return _bytesEncryptor.encrypt(bytesToEncrypt);
    }

    @Override
    public byte[] Decrypt(byte[] bytesToDencrypt) {
        return _bytesEncryptor.decrypt(bytesToDencrypt);
    }
}