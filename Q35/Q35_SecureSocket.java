import javax.net.ssl.*;
import java.net.*;
import java.io.*;
public class Q35_SecureSocket {
    public static void main(String[] args) {
        System.out.println("Illustrating Secure Sockets Procedure");
        try {
            // Client side illustration
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            try (SSLSocket socket = (SSLSocket) factory.createSocket("google.com", 443)) {
                socket.startHandshake();
                System.out.println("Successfully created secure client socket to google.com");
            } catch (Exception ex) {
                System.out.println("Could not complete handshaking: " + ex.getMessage());
            }
            
            // Server side illustration (Needs keystore, handled with exception)
            try {
                SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
                try (SSLServerSocket serverSocket = (SSLServerSocket) serverFactory.createServerSocket(8443)) {
                    System.out.println("Created secure server socket on port 8443.");
                }
            } catch (Exception ex) {
                System.out.println("Server socket creation expectedly failed without valid Keystore initialized.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}