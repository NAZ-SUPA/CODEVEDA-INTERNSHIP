import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class server {

    private static final int PORT = 12345;
    // Shared list of active clients
    private static final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("SERVER STARTED ON PORT " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);
                new Thread(handler).start();
            }
        }
    }

    // Handles a single client connection
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            String username = null;

            try {

                // 1. Initialize in/out streams

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Get the client's username

                out.println("ENTER YOUR USERNAME :");
                username = in.readLine();

                if (username == null || username.trim().isEmpty()) {
                    return;
                }

                System.out.println(username + " CONNECTED.");

                broadcast("[SERVER] " + username + " JOINED THE CHAT. ", this);

                // 2. Read messages in a loop

                String message;

                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("/quit")) {
                        break;
                    }
                    // 3. Forward/broadcast messages to other clients
                    broadcast(username + " : " + message, this);
                }

            } catch (IOException e) {
                System.out.println("CONNECTION ERROR : " + e.getMessage());
            } finally {

                // 4. Clean up streams and remove from 'clients' set upon disconnect

                clients.remove(this);
                if (username != null) {
                    broadcast("[SERVER] " + username + " LEFT THE CHAT.", this);
                    System.out.println(username + " DISCONNECTED");
                }

                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void broadcast(String messsage, ClientHandler sender) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(messsage);
                }
            }
        }
    }
}
