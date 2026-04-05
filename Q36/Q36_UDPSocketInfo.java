import java.net.*;
public class Q36_UDPSocketInfo {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(5006)) {
            System.out.println("DatagramSocket Info:");
            System.out.println("Is Bound? " + socket.isBound());
            System.out.println("Is Connected? " + socket.isConnected());
            System.out.println("Is Closed? " + socket.isClosed());
            System.out.println("Local Address: " + socket.getLocalAddress());
            System.out.println("Local Port: " + socket.getLocalPort());
            System.out.println("Send Buffer Size: " + socket.getSendBufferSize());
            System.out.println("Receive Buffer Size: " + socket.getReceiveBufferSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}