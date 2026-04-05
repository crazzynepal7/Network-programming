import java.net.*;
import java.util.Date;
public class Q39_UDPDaytime {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(5009)) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet); // Wait for client trigger
                
                String timeStr = new Date().toString();
                byte[] sendBuf = timeStr.getBytes();
                socket.send(new DatagramPacket(sendBuf, sendBuf.length, packet.getAddress(), packet.getPort()));
            } catch(Exception e) {}
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                DatagramSocket socket = new DatagramSocket();
                byte[] trigger = "time plz".getBytes();
                socket.send(new DatagramPacket(trigger, trigger.length, InetAddress.getByName("localhost"), 5009));
                
                byte[] rxBuf = new byte[256];
                DatagramPacket rxPacket = new DatagramPacket(rxBuf, rxBuf.length);
                socket.receive(rxPacket);
                System.out.println("UDP Daytime from Server: " + new String(rxPacket.getData(), 0, rxPacket.getLength()));
                socket.close();
            } catch(Exception e) {}
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}