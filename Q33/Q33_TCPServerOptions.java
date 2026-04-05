import java.net.*;
public class Q33_TCPServerOptions {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket()) {
            System.out.println("Setting TCP Server Socket Options...");
            serverSocket.setReuseAddress(true);
            serverSocket.setReceiveBufferSize(16384);
            serverSocket.setSoTimeout(10000); // 10 sec timeout for accept
            
            // bind after setting options
            serverSocket.bind(new InetSocketAddress(5005));
            
            System.out.println("SO_REUSEADDR: " + serverSocket.getReuseAddress());
            System.out.println("Receive Buffer: " + serverSocket.getReceiveBufferSize());
            System.out.println("SO_TIMEOUT: " + serverSocket.getSoTimeout());
            System.out.println("Bound Port: " + serverSocket.getLocalPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}