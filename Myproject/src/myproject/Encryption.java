package myproject;
import java.security.Security;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;




/**
 *
 * @author Joel
 */
public class Encryption {
    public String Encrypt(String message){
        return keccak(sha3(message));
    }
    /*public static void testSha3() throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    String input = "Hello world !";
    /*SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
    byte[] digest = digestSHA3.digest(input.getBytes());

    Keccak.Digest512 digestKeccak = new Keccak.Digest512();
    byte[] digest = digestKeccak.digest(input.getBytes());
    
    System.out.println("SHA3-512 = " + Hex.toHexString(digest));
}*/
    public static String sha3(String message){
       SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
       byte[] digest = digestSHA3.digest(message.getBytes());
       
       return Hex.toHexString(digest);
       
    }
    public static String keccak(String message){
        Keccak.Digest512 digestKeccak = new Keccak.Digest512();
        byte[] digest = digestKeccak.digest(message.getBytes());
        return Hex.toHexString(digest);
    }
    
}
