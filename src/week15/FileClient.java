package week15;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author spong
 */
public class FileClient {
    private MulticastSocket client;
    private InetAddress group;
    private FileDummy fd = new FileDummy();
    private int PORT = 0;
    
    public void startClient(int port) {
        PORT = port;
        try {
            client = new MulticastSocket(PORT);
            group = InetAddress.getByName("234.5.6.7");
            
            client.joinGroup(group);
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }

    public void sendFile(File myFile) {
        try {
            FileInputStream in = new FileInputStream(myFile);
            String output = "";
            for (int x = 0; x < in.getChannel().size(); x++) {
                int i = in.read();
                output += String.valueOf((char) i);
            }

            fd.setData(output);
            fd.setFilename(myFile.getName());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(fd);
            byte[] dataFD = outputStream.toByteArray();

            DatagramPacket data = new DatagramPacket(dataFD, dataFD.length, group, PORT);
            client.send(data);
            client.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
}
