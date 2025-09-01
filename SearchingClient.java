import java.net.*;
import java.util.Scanner;

public class SearchingClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);

        // Send number list
        System.out.print("Enter numbers separated by space: ");
        String numbers = scanner.nextLine();
        byte[] sendData = numbers.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9877);
        clientSocket.send(sendPacket);

        // Send number to search
        System.out.print("Enter number to search: ");
        String search = scanner.nextLine();
        sendData = search.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9877);
        clientSocket.send(sendPacket);

        // Receive response
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Server Response: " + response);
    }
}
