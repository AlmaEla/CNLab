import java.io.*;
import java.net.*;

class EchoClient {
    public static void main(String argv[]) throws Exception {
        String s;
        String m_s;

        BufferedReader inFromUser =
            new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("127.0.0.1", 4569);

        DataOutputStream outToServer =
            new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer =
            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        s = inFromUser.readLine();

        outToServer.writeBytes(s+ '\n');

        m_s= inFromServer.readLine();

        System.out.println("FROM SERVER: " + m_s);

        clientSocket.close();
    }
}
