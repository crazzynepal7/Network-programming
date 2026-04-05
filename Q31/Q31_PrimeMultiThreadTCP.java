import java.io.*;
import java.net.*;
public class Q31_PrimeMultiThreadTCP {
    private static boolean isPrime(int n) {
        if(n<=1) return false;
        for(int i=2; i*i<=n; i++) if(n%i==0) return false;
        return true;
    }
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5004)) {
                int expectedClients = 2;
                while (expectedClients > 0) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(() -> {
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            String line = in.readLine();
                            if(line != null) {
                                int num = Integer.parseInt(line);
                                System.out.println("Server checking prime for: " + num);
                                out.println(isPrime(num));
                            }
                            clientSocket.close();
                        } catch(Exception e) {}
                    }).start();
                    expectedClients--;
                }
            } catch (Exception e) {}
        });
        server.start();

        Runnable clientTask = () -> {
            try {
                Thread.sleep(500);
                Socket socket = new Socket("localhost", 5004);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                int num = (int)(Math.random() * 20);
                out.println(num);
                System.out.println("Client sent: " + num + " -> Is Prime? " + in.readLine());
                socket.close();
            } catch (Exception e) {}
        };
        Thread c1 = new Thread(clientTask);
        Thread c2 = new Thread(clientTask);
        c1.start(); c2.start();
        try { server.join(); c1.join(); c2.join(); } catch (Exception e) {}
    }
}