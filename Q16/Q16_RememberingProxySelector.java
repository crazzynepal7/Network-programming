import java.net.*;
import java.util.*;
import java.io.IOException;

public class Q16_RememberingProxySelector extends ProxySelector {
    private ProxySelector defaultSelector;
    private Set<URI> failedUris;

    public Q16_RememberingProxySelector(ProxySelector defaultSelector) {
        this.defaultSelector = defaultSelector;
        this.failedUris = new HashSet<>();
    }

    @Override
    public List<Proxy> select(URI uri) {
        if (failedUris.contains(uri)) {
            return Collections.singletonList(Proxy.NO_PROXY);
        }
        return defaultSelector.select(uri);
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        System.out.println("Connection failed to: " + uri);
        failedUris.add(uri);
    }

    public static void main(String[] args) {
        ProxySelector selector = new Q16_RememberingProxySelector(ProxySelector.getDefault());
        ProxySelector.setDefault(selector);
        System.out.println("ProxySelector initialized and set as default.");
        System.out.println("Connections to failed URIs will use NO_PROXY next time.");
    }
}