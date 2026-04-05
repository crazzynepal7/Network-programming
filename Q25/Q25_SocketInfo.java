import java.net.*;
public class Q25_SocketInfo {
    public static void main(String[] args) {
        try (Socket socket = new Socket("example.com", 80)) {
            System.out.println("Connected to " + socket.getInetAddress()
                + " on port " + socket.getPort() + " from port "
                + socket.getLocalPort() + " of "
                + socket.getLocalAddress());
        } catch (Exception e) {
            System.err.println("Could not connect to example.com: " + e.getMessage());
        }
    }
}