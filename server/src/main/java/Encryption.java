import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String encrypt(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-384");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);

        return no.toString(16);
    }
}
