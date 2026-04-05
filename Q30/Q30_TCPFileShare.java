import java.io.*;
import java.net.*;
public class Q30_TCPFileShare {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5003)) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("Server reading file contents sent by client:");
                String line;
                while((line = in.readLine()) != null) {
                    System.out.println("[File Content] " + line);
                }
                clientSocket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        Thread client = new Thread(() -> {
            try {
                // Write a dummy file first
                File f = new File("test.txt");
                PrintWriter fw = new PrintWriter(f);
                fw.println("Network");
                fw.println("Programming");
                fw.close();
                
                Thread.sleep(500);
                Socket socket = new Socket("localhost", 5003);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader fileIn = new BufferedReader(new FileReader(f));
                String line;
                while((line = fileIn.readLine()) != null) {
                    out.println(line);
                }
                socket.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}