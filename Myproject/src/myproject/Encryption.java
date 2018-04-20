package myproject;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;




/**
 *
 * @author Joel
 */
public class Encryption {
    public String Encrypt(String message) {
        return keccak(sha3(message));
    }
    
    public static String sha3(String message) {
       SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
       // Change this to UTF-16 
       digestSHA3.update(message.getBytes( StandardCharsets.UTF_8 ));
       byte[] digest = digestSHA3.digest();
       
       return Hex.toHexString(digest);
       
    }
    public static String keccak(String message){
        Keccak.Digest512 digestKeccak = new Keccak.Digest512();
        digestKeccak.update(message.getBytes( StandardCharsets.UTF_8 ));
        byte[] digest = digestKeccak.digest();
        return Hex.toHexString(digest);
    }
    
}
