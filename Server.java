import java.io.IOException;
import java.net.*;
import java.util.*;

public class Server {
    MulticastSocket socket = null;
    byte[] buffer = null;
    DatagramSocket receivePacket = null;
    Scanner scan = null;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.initializeVariable();
        server.connecting();
    }

    private void initializeVariable() {
        try {
            socket = new MulticastSocket();
            buffer = new byte[1024];
            scan = new Scanner(System.in);
            log("Server running....");
        } catch (IOException e) {
            log("initializeVariable : " + e.toString());
        }
    }

    private String readFromKeyboard() {
        String line = scan.nextLine();
        return line;
    }

    private void send(String message) throws IOException {
        InetAddress ip = InetAddress.getByName("230.0.0.0");
        buffer = message.getBytes();
        DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length, ip, 4446);
        socket.send(packetSend);
        log("Message sent");
    }

    private void connecting() throws IOException {
        while (true) {
            String data = readFromKeyboard();
            send(data);
            buffer = new byte[1024];
        }
    }

    private void log(String message) {
        System.out.println(message);
    }
}
