import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
public class Q15_URLDecoderExample {
    public static void main(String[] args) {
        try {
            String encoded = "Hello+World+%26+Java+Network+Programming+101%21";
            String decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString());
            System.out.println("Encoded : " + encoded);
            System.out.println("Decoded : " + decoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}