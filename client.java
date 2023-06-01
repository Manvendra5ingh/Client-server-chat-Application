import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                message = consoleInput.readLine();
                output.println(message);
                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
                System.out.println("Server: " + input.readLine());
            }

            input.close();
            output.close();
            consoleInput.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
