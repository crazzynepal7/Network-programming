import java.net.InetAddress;
public class Q2_InetAddressMethods {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("www.google.com");
            System.out.println("Host Name: " + ip.getHostName());
            System.out.println("Host Address: " + ip.getHostAddress());
            System.out.println("Canonical Host Name: " + ip.getCanonicalHostName());
            System.out.print("IP Address Bytes: ");
            byte[] addr = ip.getAddress();
            for (byte b : addr) {
                System.out.print((b & 0xFF) + ".");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}