import java.net.*;
import java.io.*;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
  
    public Client(String host, int port) throws Exception {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public Socket getSocket() {
        return socket;
    }

    public void handshake() throws Exception {
        out.println("12345");
        out.flush();
    }

    public String request(String number) throws Exception {
        out.println(number);
        out.flush();
        return in.readLine();
    }

    public void disconnect() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}
