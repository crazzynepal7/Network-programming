import java.io.*;
import java.net.*;
import java.util.Date;
public class Q27_TCPDaytime {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(1337)) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(new Date().toString());
                clientSocket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                Socket socket = new Socket("localhost", 1337);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Daytime from server: " + in.readLine());
                socket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}