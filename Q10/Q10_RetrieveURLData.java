import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
public class Q10_RetrieveURLData {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://example.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            System.out.println("Retrieving data from http://example.com:");
            int count = 0;
            while ((inputLine = in.readLine()) != null && count < 10) {
                System.out.println(inputLine);
                count++;
            }
            if(in.ready()) System.out.println("... (output truncated) ...");
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}