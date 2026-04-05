import java.net.URL;
public class Q12_ProtocolSupport {
    public static void main(String[] args) {
        String[] protocols = {"http", "https", "ftp", "mailto", "telnet", "file", "gopher", "ldap", "jar", "rmi"};
        System.out.println("Supported Protocols:");
        for (String p : protocols) {
            try {
                URL u = new URL(p, "example.com", "/");
                System.out.println("- " + p + " is supported.");
            } catch (Exception ex) {
                System.out.println("- " + p + " is NOT supported.");
            }
        }
    }
}