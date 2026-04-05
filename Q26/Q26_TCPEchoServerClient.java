import java.io.*;
import java.net.*;
public class Q26_TCPEchoServerClient {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Server received: " + inputLine);
                    out.println(inputLine);
                    if("bye".equalsIgnoreCase(inputLine)) break;
                }
                clientSocket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });

        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500); // Allow server to start
                Socket socket = new Socket("localhost", 5000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("Hello Echo Server");
                System.out.println("Client received: " + in.readLine());
                out.println("bye");
                System.out.println("Client received: " + in.readLine());
                socket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });

        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
        System.out.println("Echo test complete.");
    }
}