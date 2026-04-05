import java.net.*;
import java.util.List;
public class Q18_RetrieveCookies {
    public static void main(String[] args) {
        try {
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            
            URL url = new URL("http://example.com");
            URLConnection connection = url.openConnection();
            connection.getContent(); // trigger request
            
            CookieStore cookieStore = cookieManager.getCookieStore();
            List<HttpCookie> cookies = cookieStore.getCookies();
            
            System.out.println("Cookies received from " + url);
            if(cookies.isEmpty()) {
                System.out.println("No cookies set by server.");
            } else {
                for (HttpCookie cookie : cookies) {
                    System.out.println("Name: " + cookie.getName() + ", Value: " + cookie.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}