package week15;

import java.net.*;
import java.io.*;

public class ChatClient {

    private MulticastSocket chat;
    private InetAddress group;
    private int PORT = 0;

    public void startClient(int port) {
        PORT = port;
        try {
            chat = new MulticastSocket(PORT);
            group = InetAddress.getByName("234.5.6.7");

            chat.joinGroup(group);
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }

    public void sendMsg(String msg) {
        try {
            DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, PORT);
            chat.send(data);
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }
}
