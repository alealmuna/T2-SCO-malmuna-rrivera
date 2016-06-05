import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMAC {
    final static String testKey = "testing-key!";
    
    public static void main(String[] args) throws Exception {
        System.out.println(hmacDigest("Esta es una prueba para el curso del PET", testKey, "HmacMD5"));
    }

    public static String hmacDigest(String msg, String keyString, String cryptoFunc) {
        String output = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), cryptoFunc);
            Mac mac = Mac.getInstance(cryptoFunc);
            mac.init(key);
            
            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));
            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            output = hash.toString();
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        return output;
    }
}
