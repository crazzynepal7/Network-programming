import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
public class Q7_IPFromMAC {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            System.out.println("Retrieving IP from Interfaces...");
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                byte[] mac = ni.getHardwareAddress();
                if(mac == null) continue;
                System.out.print("Interface: " + ni.getName() + " -> ");
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                if(addresses.hasMoreElements()) {
                    System.out.println("IP: " + addresses.nextElement().getHostAddress());
                } else {
                    System.out.println("No IP bound");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}