import java.net.*;
public class Q40_UDPTextShare {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(5010)) {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("UDP Server Shared Text: " + msg);
            } catch(Exception e) {}
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                DatagramSocket socket = new DatagramSocket();
                String txt = "This is a shared text over UDP.";
                byte[] buf = txt.getBytes();
                socket.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 5010));
                socket.close();
            } catch(Exception e) {}
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}