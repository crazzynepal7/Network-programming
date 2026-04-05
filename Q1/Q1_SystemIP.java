import java.net.InetAddress;
public class Q1_SystemIP {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("System IP Address: " + ip.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}