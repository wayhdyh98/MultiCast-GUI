package week15;

import java.net.*;
import java.io.*;

public class ChatClient {

    private MulticastSocket chat;
    private InetAddress group;

    public void startClient(int port) {
        try {
            chat = new MulticastSocket(port);
            group = InetAddress.getByName("234.5.6.7");

            chat.joinGroup(group);
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }

    public void sendMsg(String msg) {
        try {
            DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, 1234);
            chat.send(data);
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }
}
