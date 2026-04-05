import java.net.*;
import java.util.*;
public class Q22_EntireHTTPHeader {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            URLConnection conn = url.openConnection();
            Map<String, List<String>> headers = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey() == null ? "Status" : entry.getKey();
                System.out.println(key + ": " + String.join(", ", entry.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}