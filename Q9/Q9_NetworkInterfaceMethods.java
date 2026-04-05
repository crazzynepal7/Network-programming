import java.net.NetworkInterface;
import java.net.InetAddress;
import java.util.Enumeration;
public class Q9_NetworkInterfaceMethods {
    public static void main(String[] args) {
        try {
            System.out.println("Factory methods test (getByName):");
            NetworkInterface ni = NetworkInterface.getByName("lo"); 
            if(ni != null) {
                System.out.println("Found 'lo'. Display Name: " + ni.getDisplayName());
            } else {
                System.out.println("Interface 'lo' not found.");
            }
            System.out.println("\nGetter methods test for first available interface:");
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            if(nis.hasMoreElements()) {
                NetworkInterface first = nis.nextElement();
                System.out.println("getName(): " + first.getName());
                System.out.println("getDisplayName(): " + first.getDisplayName());
                System.out.println("getIndex(): " + first.getIndex());
                System.out.println("isUp(): " + first.isUp());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}