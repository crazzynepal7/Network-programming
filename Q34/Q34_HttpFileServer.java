import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
public class Q34_HttpFileServer {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String response = "<h1>Hello from HTTP File Server</h1><p>Served successfully!</p>";
                    exchange.sendResponseHeaders(200, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            });
            server.setExecutor(null);
            server.start();
            System.out.println("HTTP Server started on port 8000.");
            
            // Client request
            URL url = new URL("http://localhost:8000/");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            System.out.println("Client received: " + in.readLine());
            in.close();
            
            server.stop(0);
        } catch (Exception e) { e.printStackTrace(); }
    }
}