import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
public class Q21_DownloadCharset {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://example.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String contentType = conn.getContentType();
            String charset = "UTF-8"; // default
            if (contentType != null && contentType.contains("charset=")) {
                charset = contentType.split("charset=")[1];
            }
            System.out.println("Charset decided: " + charset);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName(charset)));
            String inputLine;
            int count = 0;
            while ((inputLine = in.readLine()) != null && count < 5) {
                System.out.println(inputLine);
                count++;
            }
            if(in.ready()) System.out.println("...");
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}