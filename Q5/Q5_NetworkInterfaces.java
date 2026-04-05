import java.net.NetworkInterface;
import java.util.Enumeration;
public class Q5_NetworkInterfaces {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            System.out.println("Available Network Interfaces:");
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                System.out.println("- " + ni.getName() + " [" + ni.getDisplayName() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}