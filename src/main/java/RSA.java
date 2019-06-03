import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class RSA {

    static String mesage;
    static String code;
    static String decode;

    Cipher cipher;
    Cipher dcipher;


    RSA(KeyPair pair) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        cipher = Cipher.getInstance("RSA");
        dcipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,pair.getPublic());
        dcipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
    }

    public String doEncript(String string) throws UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        byte[] utf8 = string.getBytes("UTF8");
        byte[] encript = cipher.doFinal(utf8);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(encript);
    }

    public String doDecript(String string) throws IOException, BadPaddingException, IllegalBlockSizeException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] decript = base64Decoder.decodeBuffer(string);
        byte[] utf8 = dcipher.doFinal(decript);
        return new String(utf8, "UTF8");
    }

}
