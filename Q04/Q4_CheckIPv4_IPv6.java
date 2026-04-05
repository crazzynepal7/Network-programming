import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
public class Q4_CheckIPv4_IPv6 {
    public static void main(String[] args) {
        String[] addresses = {"192.168.1.1", "2001:0db8:85a3:0000:0000:8a2e:0370:7334"};
        for(String addrStr : addresses) {
            try {
                InetAddress address = InetAddress.getByName(addrStr);
                if (address instanceof Inet4Address) {
                    System.out.println(addrStr + " is an IPv4 Address.");
                } else if (address instanceof Inet6Address) {
                    System.out.println(addrStr + " is an IPv6 Address.");
                }
            } catch(Exception e) {
                System.out.println("Invalid address: " + addrStr);
            }
        }
    }
}