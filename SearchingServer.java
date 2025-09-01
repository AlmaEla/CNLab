import java.net.*;
import java.util.StringTokenizer;

public class SearchingServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9877);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("Searching Server is running...");

        while (true) {
            // Receive number list
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String numberList = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Receive number to search
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String searchStr = new String(receivePacket.getData(), 0, receivePacket.getLength());
            int searchNum = Integer.parseInt(searchStr);

            boolean found = false;
            StringTokenizer st = new StringTokenizer(numberList, " ");
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == searchNum) {
                    found = true;
                    break;
                }
            }

            String response = found ? "Number Found" : "Number Not Found";

            // Send response
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        }
    }
}
