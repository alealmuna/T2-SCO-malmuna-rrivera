import java.io.*;
import java.net.*;
import java.net.InetAddress;
import java.util.Scanner;


class Client {
    final static String cryptoKey = "SCOalmuna-rivera";

    public static void checkMsg(String msg, String h){
        String mac = HMAC.hmacDigest(msg, cryptoKey, "HmacMD5");
		System.out.println("Calculated authentication code: " + mac);
		System.out.println("Received authentication code: " + h);
        if(mac.equals(h)){
		    System.out.println("Message received safely.");
        } else{
		    System.out.println("Message was NOT authenticated succesfully.");
		    System.out.println("Client is shutting down.");
            System.exit(-1);
        }
    }
    public static void main(String args[]) {

         Socket sock=null;
         DataInputStream dis=null;
         PrintStream ps=null;
         String hostname = "localhost";
         Scanner scanner = new Scanner(System.in);
         System.out.print("TCP server address (press Enter to use localhost): ");
         String readHost = scanner.nextLine();

         if (!readHost.isEmpty()) {
             hostname = readHost;
         }

         try {
 
			System.out.println("Connecting to " + hostname + " on port " + Server.PORT);
			InetAddress ip =InetAddress.getByName(hostname);

            sock= new Socket(ip, Server.PORT); ps= new 
            PrintStream(sock.getOutputStream());

    		BufferedReader is = new BufferedReader(new 
                    InputStreamReader(sock.getInputStream()));

            System.out.print("Enter message to send: ");
            String msg = scanner.next();
			ps.println(msg);
            String recv_mac = is.readLine();
            checkMsg(msg, recv_mac);
          }catch(SocketException e){ System.out.println("SocketException " + e); 
          }catch(IOException e){ System.out.println("IOException " + e);

	      } finally{
			  try{
				  sock.close(); 
			  } catch(IOException ie){ 
					System.out.println("IO exception:" + ie.getMessage()); 
			  } 
		  }
    }
 }
