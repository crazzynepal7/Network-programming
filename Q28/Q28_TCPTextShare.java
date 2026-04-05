import java.io.*;
import java.net.*;
public class Q28_TCPTextShare {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5001)) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("Server received message: " + in.readLine());
                clientSocket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                Socket socket = new Socket("localhost", 5001);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("Client sending: Networking is fun!");
                out.println("Networking is fun!");
                socket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}