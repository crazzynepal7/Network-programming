import java.net.URL;
public class Q11_SplitURL {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com:8080/docs/resource1.html?name=test#hash");
            System.out.println("URL: " + url.toString());
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("File: " + url.getFile());
            System.out.println("Query: " + url.getQuery());
            System.out.println("Ref (Fragment): " + url.getRef());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}