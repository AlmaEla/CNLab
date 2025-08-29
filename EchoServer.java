import java.io.*;
import java.net.*;

class EchoServer {
    public static void main(String argv[]) throws Exception {
        String c_s;
        String echo_s;

        ServerSocket welcome= new ServerSocket(4569);
        System.out.println("Server started... Waiting for client connection");

        while (true) {
            Socket connectionSocket = welcome.accept();
            BufferedReader in =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream out =
                new DataOutputStream(connectionSocket.getOutputStream());

            c_s = in.readLine();

            echo_s = "Echo: " + c_s + '\n';
            out.writeBytes(echo_s);
            System.out.println("server: "+c_s);
        }
    }
}
