import java.net.*;
public class Q44_MulticastSniffer {
    public static void main(String[] args) {
        System.out.println("Starting Multicast Sniffer on 224.0.0.2:5014...");
        Thread sniffer = new Thread(() -> {
            try {
                MulticastSocket socket = new MulticastSocket(5014);
                socket.setSoTimeout(3000);
                InetSocketAddress group = new InetSocketAddress("224.0.0.2", 5014);
                NetworkInterface netIf = NetworkInterface.getByInetAddress(InetAddress.getLoopbackAddress());
                if(netIf != null) socket.joinGroup(group, netIf);
                
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("Sniffer Intercepted: " + new String(packet.getData(), 0, packet.getLength()));
                
                socket.close();
            } catch (Exception e) {
                System.out.println("Sniffer caught exception (expected if no multicast configured): " + e.getMessage());
            }
        });
        
        Thread sender = new Thread(() -> {
            try {
                Thread.sleep(500);
                DatagramSocket socket = new DatagramSocket();
                byte[] buf = "Secret Multicast Data".getBytes();
                socket.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("224.0.0.2"), 5014));
                socket.close();
            } catch (Exception e) {}
        });
        sniffer.start();
        sender.start();
        try { sniffer.join(); sender.join(); } catch (Exception e) {}
    }
}