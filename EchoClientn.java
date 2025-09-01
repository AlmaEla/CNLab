import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();
            byte[] sendData = message.getBytes();
            byte[] receiveData = new byte[1024];

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Echo from Server: " + response);
        }
    }
}
