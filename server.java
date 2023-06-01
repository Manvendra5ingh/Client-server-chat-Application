import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started. Waiting for client connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while (true) {
                if ((message = input.readLine()) != null) {
                    System.out.println("Client: " + message);
                    output.println("Server received: " + message);
                    if (message.equalsIgnoreCase("bye")) {
                        break;
                    }
                }
            }

            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
