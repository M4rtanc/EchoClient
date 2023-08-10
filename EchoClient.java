import java.io.*;
import java.net.*;

public class EchoClient {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private BufferedReader inputReader;
    private PrintWriter toServer;
    private BufferedReader fromServer;

    public EchoClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void connect() {
        try {
            socket = new Socket(serverIP, serverPort);
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Your input: ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String userInput;
            while ((userInput = inputReader.readLine()) != null) {
            	toServer.println(userInput);
                String serverResponse = fromServer.readLine();
                System.out.println("Server response: " + serverResponse);
                System.out.println("Your input: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverIP = "78.128.211.58";
        int serverPort = 4444;

        EchoClient client = new EchoClient(serverIP, serverPort);
        client.connect();
        client.run();
        client.close();
    }
}
