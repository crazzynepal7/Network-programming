import java.net.*;
public class Q17_GovCookiePolicy implements CookiePolicy {
    @Override
    public boolean shouldAccept(URI uri, HttpCookie cookie) {
        if (uri.getHost() != null && uri.getHost().endsWith(".gov")) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(new Q17_GovCookiePolicy());
        CookieHandler.setDefault(manager);
        System.out.println("Cookie policy set: Blocking cookies from .gov domains.");
        
        try {
            URI govUri = new URI("https://www.nsa.gov");
            HttpCookie c1 = new HttpCookie("test", "123");
            boolean acceptGov = new Q17_GovCookiePolicy().shouldAccept(govUri, c1);
            System.out.println("Accept from nsa.gov? " + acceptGov);
            
            URI orgUri = new URI("https://www.example.org");
            boolean acceptOrg = new Q17_GovCookiePolicy().shouldAccept(orgUri, c1);
            System.out.println("Accept from example.org? " + acceptOrg);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}