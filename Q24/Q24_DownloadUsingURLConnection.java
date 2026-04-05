import java.io.*;
import java.net.*;
public class Q24_DownloadUsingURLConnection {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://example.com");
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            System.out.println("Reading webpage via URLConnection:");
            for (int i=0; i<6; i++) { // Print first few lines
                if ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println("... Done downloading.");
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}