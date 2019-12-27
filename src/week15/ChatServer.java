package week15;

import java.io.IOException;
import java.net.*;

public class ChatServer {
    private MulticastSocket server;
    private InetAddress group;
    
    public void startServer(int port){
        try {
            server = new MulticastSocket(port);
            group = InetAddress.getByName("234.5.6.7");
            
            server.joinGroup(group);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public String getMsg(){
        String msg = "";
        try {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf,buf.length);
            server.receive(data);
            msg = new String(data.getData()).trim();
        } catch (IOException e) {
            System.err.println(e);
        }
        return msg;
    }
}
