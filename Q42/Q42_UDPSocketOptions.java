import java.net.*;
public class Q42_UDPSocketOptions {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(5012)) {
            System.out.println("Setting UDP DatagramSocket Options...");
            socket.setSoTimeout(5000);
            socket.setReceiveBufferSize(65536);
            socket.setSendBufferSize(65536);
            socket.setReuseAddress(true);
            socket.setBroadcast(true);
            
            System.out.println("SO_TIMEOUT: " + socket.getSoTimeout());
            System.out.println("Receive Buffer: " + socket.getReceiveBufferSize());
            System.out.println("Send Buffer: " + socket.getSendBufferSize());
            System.out.println("SO_REUSEADDR: " + socket.getReuseAddress());
            System.out.println("SO_BROADCAST: " + socket.getBroadcast());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}