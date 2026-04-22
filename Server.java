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
        for (int i = 0; i < numClients; i++) {
            Socket client = serverSocket.accept();
            Thread t = new Thread(() -> {
                try {
                    handleClient(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }

    }

    private void handleClient(Socket client) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());


        String handshake = in.readLine();
        if (!handshake.equals("12345")) {
            out.println("couldn't handshake");
            out.flush();
            client.close();
            connectedTimes.add(LocalDateTime.now());
            return;
        }

        String input = in.readLine();
        try {
            int number = Integer.parseInt(input);
            int count = countFactors(number);
            out.println("The number " + number + " has " + count + " factors");
            out.flush();
        } catch (Exception e) {
            out.println("There was an exception on the server");
            out.flush();
        }

        connectedTimes.add(LocalDateTime.now());
        client.close();
    }

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
    public ArrayList<LocalDateTime> getConnectedTimes() {
        ArrayList<LocalDateTime> sorted = new ArrayList<>(connectedTimes);
        Collections.sort(sorted);
        return sorted;
    }

    public void disconnect() {
        try {
        serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

  
