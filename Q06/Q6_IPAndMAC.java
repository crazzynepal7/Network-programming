import java.net.InetAddress;
import java.net.NetworkInterface;
public class Q6_IPAndMAC {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            if (mac == null) {
                System.out.println("MAC address not found for this interface.");
            } else {
                System.out.print("Current MAC address : ");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                System.out.println(sb.toString());
            }
        } catch (Exception e) {
            System.err.println("Error acquiring IP or MAC: " + e.getMessage());
        }
    }
}