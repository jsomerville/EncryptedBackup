/**
 * 
 */
package org.somerville.james;

/**
 * @author jsomerville
 *
 */
public interface CryptorService {
	byte[] Encrypt(byte[] bytesToEncrypt);
	byte[] Decrypt(byte[] bytesToDencrypt);
}
