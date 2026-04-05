import java.net.*;
public class Q32_TCPClientOptions {
    public static void main(String[] args) {
        try (Socket socket = new Socket()) {
            System.out.println("Setting TCP Client Socket Options...");
            socket.setSoTimeout(5000);
            socket.setTcpNoDelay(true);
            socket.setSoLinger(true, 10);
            socket.setReceiveBufferSize(8192);
            socket.setSendBufferSize(8192);
            socket.setKeepAlive(true);
            
            System.out.println("SO_TIMEOUT: " + socket.getSoTimeout());
            System.out.println("TCP_NODELAY: " + socket.getTcpNoDelay());
            System.out.println("SO_LINGER: " + socket.getSoLinger());
            System.out.println("Receive Buffer: " + socket.getReceiveBufferSize());
            System.out.println("Send Buffer: " + socket.getSendBufferSize());
            System.out.println("SO_KEEPALIVE: " + socket.getKeepAlive());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}