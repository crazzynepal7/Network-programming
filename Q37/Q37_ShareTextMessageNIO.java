import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
public class Q37_ShareTextMessageNIO {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            try {
                Selector selector = Selector.open();
                ServerSocketChannel serverChannel = ServerSocketChannel.open();
                serverChannel.bind(new InetSocketAddress("localhost", 5007));
                serverChannel.configureBlocking(false);
                serverChannel.register(selector, SelectionKey.OP_ACCEPT);

                boolean running = true;
                while (running) {
                    selector.select(1000);
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();
                        if (key.isAcceptable()) {
                            SocketChannel client = serverChannel.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(256);
                            int bytesRead = client.read(buffer);
                            if (bytesRead == -1) {
                                client.close();
                            } else {
                                System.out.println("NIO Server received: " + new String(buffer.array()).trim());
                                running = false;
                                client.close();
                            }
                        }
                    }
                }
                serverChannel.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        Thread client = new Thread(() -> {
            try {
                Thread.sleep(500);
                SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("localhost", 5007));
                String msg = "Hello NIO!";
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                clientChannel.write(buffer);
                clientChannel.close();
            } catch (Exception e) { e.printStackTrace(); }
        });
        server.start();
        client.start();
        try { server.join(); client.join(); } catch (Exception e) {}
    }
}