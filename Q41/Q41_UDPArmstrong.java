import java.net.*;
public class Q41_UDPArmstrong {
    private static boolean isArmstrong(int number) {
        int original = number;
        int result = 0;
        int n = String.valueOf(number).length();
        while (original != 0) {
            int remainder = original % 10;
            result += Math.pow(remainder, n);
            original /= 10;
        }
        return result == number;
    }
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(5011)) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                int num = Integer.parseInt(msg);
                System.out.println("Server checking Armstrong for: " + num);
                
                String reply = isArmstrong(num) ? "Yes, it is an Armstrong number." : "No, it is not an Armstrong number.";
                byte[] sendBuf = reply.getBytes();
                socket.send(new DatagramPacket(sendBuf, sendBuf.length, packet.getAddress(), packet.getPort()));
            } catch(Exception e) {}
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                DatagramSocket socket = new DatagramSocket();
                int num = 153;
                byte[] buf = String.valueOf(num).getBytes();
                socket.send(new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 5011));
                
                byte[] rxBuf = new byte[256];
                DatagramPacket rxPacket = new DatagramPacket(rxBuf, rxBuf.length);
                socket.receive(rxPacket);
                System.out.println("Client Sent: " + num);
                System.out.println("Client Received: " + new String(rxPacket.getData(), 0, rxPacket.getLength()));
                socket.close();
            } catch(Exception e) {}
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}