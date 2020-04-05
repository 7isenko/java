
import lab5.SHA1Encoder;

import java.util.Random;

public class CryptoPasswordGenerator {

    public static String[] gen(){
        String password;
        String encodedPassword;

        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuffer sb = new StringBuffer(8);
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        password = sb.toString();
        encodedPassword = SHA1Encoder.encryptPassword(password);
        return new String[]{password, encodedPassword};
    }


}
