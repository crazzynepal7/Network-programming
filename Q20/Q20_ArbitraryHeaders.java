import java.net.URL;
import java.net.URLConnection;
public class Q20_ArbitraryHeaders {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            URLConnection connection = url.openConnection();
            
            System.out.println("Arbitrary Headers from URLConnection:");
            for (int i = 0;; i++) {
                String headerName = connection.getHeaderFieldKey(i);
                String headerValue = connection.getHeaderField(i);
                if (headerName == null && headerValue == null) {
                    break;
                }
                if (headerName == null) {
                    System.out.println("Status Line: " + headerValue);
                } else {
                    System.out.println(headerName + ": " + headerValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}