import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("CONNECT TO SERVER");

            // Thread 1: Listen to server messages
            new Thread(() -> {
                try {
                    String server_message;
                    while ((server_message = in.readLine()) != null) {
                        System.out.println(server_message);
                    }
                } catch (Exception e) {
                    System.out.println("DISCONNECTED FROM SERVER.");
                }
            }).start();

            // Main Thread: Send console input to server
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                out.println(input);
            }
        }
    }
}
