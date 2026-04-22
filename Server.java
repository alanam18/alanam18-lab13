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
  
