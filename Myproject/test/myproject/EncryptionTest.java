/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joel
 */
public class EncryptionTest {
    
    public EncryptionTest() {
    }

    @Test
    public void testSha3() {
        System.out.println("sha3");
        String message = "joel";
        String expResult = "849dbb330063097bbd0b1bb15c4eab55fb68574cd826000b8d3be2a812a19c9cf32c3bfce09dc899f9af73514b3b59b1b207bc5333e500c926c96e90a4dad6b8";
        String result = Encryption.sha3(message);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testKeccak() {
        System.out.println("keccak");
        String message = "joel";
        String expResult = "cddfa5c7b44c38fecd581ce289c9a5a9aff45382753b21160d62545f9cad8b48da38147574fec9ce43b6db58baa83072dd837355162ea4d167622754523c7854";
        String result = Encryption.keccak(message);
        assertEquals(expResult, result);
            }

    @Test
    public void testEncrypt() {
        System.out.println("Encrypt");
        String message = "joel";
        Encryption instance = new Encryption();
        String expResult = "6916bfabcd5a1e7dd87ee30d0031ea27a8d1e744538aa3424391cf67f7a35f2ad959b2b07d618e2e56dd6d37d9c4c99c357ea490f8fd4ba9738225698e066b07";
        String result = instance.Encrypt(message);
        assertEquals(expResult, result);
        
    }
    
}
