import java.net.*;
public class Q38_UDPEcho {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(5008)) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("UDP Server received: " + msg);
                socket.send(new DatagramPacket(packet.getData(), packet.getLength(), packet.getAddress(), packet.getPort()));
            } catch(Exception e) {}
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                DatagramSocket socket = new DatagramSocket();
                String msg = "Echo this";
                byte[] buf = msg.getBytes();
                InetAddress address = InetAddress.getByName("localhost");
                socket.send(new DatagramPacket(buf, buf.length, address, 5008));
                
                byte[] rxBuf = new byte[256];
                DatagramPacket rxPacket = new DatagramPacket(rxBuf, rxBuf.length);
                socket.receive(rxPacket);
                System.out.println("UDP Client received: " + new String(rxPacket.getData(), 0, rxPacket.getLength()));
                socket.close();
            } catch(Exception e) {}
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}