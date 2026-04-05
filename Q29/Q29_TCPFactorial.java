import java.io.*;
import java.net.*;
public class Q29_TCPFactorial {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5002)) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                int num = Integer.parseInt(in.readLine());
                System.out.println("Server computing factorial for: " + num);
                long fact = 1;
                for(int i=2; i<=num; i++) fact *= i;
                out.println(fact);
                clientSocket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                Socket socket = new Socket("localhost", 5002);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                int num = 5;
                out.println(num);
                System.out.println("Client sent: " + num);
                System.out.println("Client received factorial: " + in.readLine());
                socket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}