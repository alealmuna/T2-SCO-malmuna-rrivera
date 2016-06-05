
import java.net.*;
import java.io.*;

public class Server {

    public static final int PORT = 9025;

    public static void main(String args[]) {

        ServerSocket sersock = null;
        Socket sock = null;
        System.out.println("Initializing server on port " + PORT);

        try {
            sersock = new ServerSocket(PORT);

            while(true){
			    System.out.println("Server listening for connection..."); 
                sock = sersock.accept();
			    System.out.println("Received connection from " + sock.getInetAddress() + " on port " + sock.getPort()); 
			    //DataInputStream is = null;
			    //DataOutputStream os = null;

                BufferedReader is = new BufferedReader(
                        new InputStreamReader(sock.getInputStream()));

                System.out.println(is.readLine());

                PrintStream ios = new PrintStream(sock.getOutputStream());
                ios.println("Init message from server");
                ios.close();

                sock.close();
            }
        } catch (SocketException se) {
            System.out.println("Problems with the server " + se.getMessage());
        } catch(IOException e1){
			System.out.println("Listen socket: " + e1.getMessage());
        } catch (Exception e) {
            System.out.println("Error when initializing " + e.getMessage());
        }

        System.out.println("Connection from : " + sock.getInetAddress());

    }
}
