import java.net.*;
import java.io.IOException;
public class Q43_MulticastCommunication {
    public static void main(String[] args) {
        System.out.println("Illustrating Multicast Group Communication...");
        Thread receiver = new Thread(() -> {
            try {
                MulticastSocket socket = new MulticastSocket(5013);
                socket.setSoTimeout(3000);
                InetSocketAddress group = new InetSocketAddress("224.0.0.1", 5013);
                NetworkInterface netIf = NetworkInterface.getByName("lo"); // Using loopback for test
                if(netIf != null) socket.joinGroup(group, netIf);
                
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("Multicast Receiver got: " + new String(packet.getData(), 0, packet.getLength()));
                
                if(netIf != null) socket.leaveGroup(group, netIf);
                socket.close();
            } catch (Exception e) {}
        });
        Thread sender = new Thread(() -> {
            try {
                Thread.sleep(1000);
                DatagramSocket socket = new DatagramSocket();
                String msg = "Hello Multicast Group!";
                byte[] buf = msg.getBytes();
                socket.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("224.0.0.1"), 5013));
                System.out.println("Multicast Sender sent data.");
                socket.close();
            } catch (Exception e) {}
        });
        receiver.start();
        sender.start();
        try { receiver.join(); sender.join(); } catch (Exception e) {}
    }
}