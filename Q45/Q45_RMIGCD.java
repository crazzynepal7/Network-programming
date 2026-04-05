import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Q45_RMIGCD {
    // 1. Interface
    public interface GCDInterface extends Remote {
        int calculateGCD(int a, int b) throws RemoteException;
    }

    // 2. Implementation
    public static class GCDImpl extends UnicastRemoteObject implements GCDInterface {
        protected GCDImpl() throws RemoteException { super(); }
        @Override
        public int calculateGCD(int a, int b) throws RemoteException {
            System.out.println("RMI Server computing GCD for " + a + " and " + b);
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }
    }

    // Main encapsulating both Server and Client for demonstration
    public static void main(String[] args) {
        try {
            // Start RMI Registry internally
            Registry registry = LocateRegistry.createRegistry(1099);
            GCDInterface stub = new GCDImpl();
            registry.bind("GCDService", stub);
            System.out.println("RMI Registry started and GCDService bound.");

            // RMI Client
            Registry clientRegistry = LocateRegistry.getRegistry("localhost", 1099);
            GCDInterface remoteGCD = (GCDInterface) clientRegistry.lookup("GCDService");
            int a = 48, b = 18;
            int result = remoteGCD.calculateGCD(a, b);
            System.out.println("RMI Client: GCD of " + a + " and " + b + " is " + result);
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}