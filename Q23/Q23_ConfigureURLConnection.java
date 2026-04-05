import java.net.*;
public class Q23_ConfigureURLConnection {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setAllowUserInteraction(false);
            conn.setDoOutput(true); // Can send data
            System.out.println("URLConnection configured successfully.");
            System.out.println("Read Timeout: " + conn.getReadTimeout());
            System.out.println("Connect Timeout: " + conn.getConnectTimeout());
            System.out.println("Do Output: " + conn.getDoOutput());
            System.out.println("Allow User Interaction: " + conn.getAllowUserInteraction());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}