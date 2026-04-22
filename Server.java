import java.net.*;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private ServerSocket serverSocket;
    private List<LocalDateTime> connectedTimes = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }

    public void serve(int numClients) throws Exception {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numClients; i++) {
            Socket client = serverSocket.accept();
            Thread t = new Thread(() -> {
                try {
                    handleClient(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    private void handleClient(Socket client) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());

        private int countFactors(int n) {
            int count = 0;
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    if (i == n / i) {
                        count += 1;
                    } else {
                        count += 2;
                    }
                }
            }
            return count;
        }
}
  
