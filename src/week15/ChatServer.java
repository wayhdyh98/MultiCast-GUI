package week15;
import java.net.*;

public class ChatServer {

    public static void main(String args[]) throws Exception {
        MulticastSocket server = new MulticastSocket(1234);
        InetAddress group = InetAddress.getByName("234.5.6.7");
        //getByName – Mengembalikan alamat IP yang diberikan oleh Host
        server.joinGroup(group);
        boolean infinite = true;
        /* Server terus-menerus menerima data dan mencetak mereka */
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf,buf.length);
            server.receive(data);
            String msg = new String(data.getData()).trim();
            System.out.println(msg);
        }
        server.close();
    }
}
