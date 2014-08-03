package org.somerville.james;

import org.apache.commons.lang.NullArgumentException;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.hamcrest.core.*;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Created by jsomerville on 7/5/14.
 */
public class SpringCryptorServiceImplTest {
    private static final String password = "password";
    private static final String sampleText = "This is an encryption test.";

    @Test
    public void CryptorPostConstructorSaltNotNull() {
        SpringCryptorServiceImpl cryptor = new SpringCryptorServiceImpl(password);
        assertNotNull(cryptor.getSalt());
    }

    @Test
    public void CryptorSaltIsConstant() {
        SpringCryptorServiceImpl cryptor = new SpringCryptorServiceImpl(password);
        String saltAccess1 = cryptor.getSalt();
        String saltAccess2 = cryptor.getSalt();
        assertSame(saltAccess1, saltAccess2);
    }

    @Test(expected = NullArgumentException.class)
    public void CryptorConstructorPasswordParamNotNull() {
        SpringCryptorServiceImpl cryptor = new SpringCryptorServiceImpl(null);
    }

    @Test
    public void CryptorIsEncrypting() {
        byte[] originalBytes = sampleText.getBytes(Charset.forName("UTF-8"));

        SpringCryptorServiceImpl cryptor = new SpringCryptorServiceImpl(password);
        byte[] encryptedBytes = cryptor.Encrypt(originalBytes);

        assertThat(encryptedBytes, not(equalTo(originalBytes)));

        String encryptedString = new String(encryptedBytes);
        assertNotEquals(encryptedString, sampleText);
    }

    @Test
    public void CryptorIsDecrypting() {
        byte[] originalBytes = sampleText.getBytes(Charset.forName("UTF-8"));

        SpringCryptorServiceImpl cryptor = new SpringCryptorServiceImpl(password);
        byte[] encryptedBytes = cryptor.Encrypt(originalBytes);
        byte[] decryptedBytes = cryptor.Decrypt(encryptedBytes);

        assertArrayEquals(originalBytes, decryptedBytes);

        String decryptedString = new String(decryptedBytes);
        assertEquals(decryptedString, sampleText);
    }
}