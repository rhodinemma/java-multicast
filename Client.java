import java.io.IOException;
import java.net.*;

public class Client {
    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramPacket packet = null;
    InetAddress ip = null;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.initializeVariable();
        client.connecting();
    }

    private void initializeVariable() throws IOException {
        socket = new MulticastSocket(4446);
        ip = InetAddress.getByName("230.0.0.0");
        buffer = new byte[1024];
    }

    private String receiveData() throws IOException {
        String line = "";
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        line = new String(packet.getData(), 0, packet.getLength());
        return line;
    }

    private void joinGroup() throws IOException {
        socket.joinGroup(ip);
        log("Client running...");
    }

    private void connecting() throws IOException {
        joinGroup();
        while (true) {
            String line = receiveData();
            log("Client received...." + line);
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
