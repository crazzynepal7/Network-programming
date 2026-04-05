import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
public class Q14_URLEncoderExample {
    public static void main(String[] args) {
        try {
            String original = "Hello World & Java Network Programming 101!";
            String encoded = URLEncoder.encode(original, StandardCharsets.UTF_8.toString());
            System.out.println("Original: " + original);
            System.out.println("Encoded : " + encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}