import java.net.InetAddress;
public class Q3_IPCharacteristics {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            System.out.println("Characteristics for " + address.getHostAddress());
            System.out.println("Is Any Local Address? " + address.isAnyLocalAddress());
            System.out.println("Is Loopback Address? " + address.isLoopbackAddress());
            System.out.println("Is Link Local Address? " + address.isLinkLocalAddress());
            System.out.println("Is Site Local Address? " + address.isSiteLocalAddress());
            System.out.println("Is Multicast Address? " + address.isMulticastAddress());
        } catch(Exception e){
            System.out.println(e);
        }
    }
}