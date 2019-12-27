package week15;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;

/**
 *
 * @author spong
 */
public class FileServer {
    private MulticastSocket server;
    private InetAddress group;
    private FileDummy fd = null;
    private String path = "";
    
    public void startServer(int port){
        try {
            server = new MulticastSocket(port);
            group = InetAddress.getByName("234.5.6.7");
            
            server.joinGroup(group);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public String getMsg(String path){
        String msg = "";
        try {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            server.receive(data);

            byte[] dataFD = data.getData();
            ByteArrayInputStream in = new ByteArrayInputStream(dataFD);
            ObjectInputStream is = new ObjectInputStream(in);
            fd = (FileDummy) is.readObject();

            File myFile = new File(path + "\\" + fd.getFilename());

            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            PrintWriter printWriter = new PrintWriter(myFile);
            printWriter.println(fd.getData());
            printWriter.close();
            msg = "File " + fd.getFilename() + " telah dikirim.";
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return msg;
    }
}
