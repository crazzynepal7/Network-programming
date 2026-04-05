import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
public class Q19_HeaderSpecificMethods {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            URLConnection connection = url.openConnection();
            
            System.out.println("Specific Header Methods Results:");
            System.out.println("Content-Type: " + connection.getContentType());
            System.out.println("Content-Length: " + connection.getContentLength());
            System.out.println("Content-Encoding: " + connection.getContentEncoding());
            System.out.println("Date: " + new Date(connection.getDate()));
            System.out.println("Last Modified: " + new Date(connection.getLastModified()));
            System.out.println("Expiration: " + connection.getExpiration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}