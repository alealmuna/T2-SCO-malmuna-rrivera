import java.io.*;
import java.net.*;
import java.net.InetAddress;


class Client {
    final static String cryptoKey = "SCOalmuna-rivera";

    public static void main(String args[]) {

         Socket sock=null;
         DataInputStream dis=null;
         PrintStream ps=null;
         String hostname = "localhost";

         if(args.length == 2){
		    hostname = args[1];
         } else if((args.length != 0)){
             System.out.println("Wrong number of arguments");
             System.out.println("Usage: java –jar client.jar [-host <hostname>]");
             System.exit(-1);
         }

         try {
 
			System.out.println("Connecting to " + hostname + " on port " + Server.PORT);
			InetAddress ip =InetAddress.getByName(hostname);

            sock= new Socket(ip, Server.PORT); ps= new 
            PrintStream(sock.getOutputStream());
            String initMsg = "Init message from client";
			System.out.println("Sending message...");
			ps.println(initMsg);

    		BufferedReader is = new BufferedReader(new 
                    InputStreamReader(sock.getInputStream()));

            String mac = HMAC.hmacDigest(initMsg, cryptoKey, "HmacMD5");
			System.out.println("Calculated authentication code: " + mac);
            String recv_mac = is.readLine();
			System.out.println("Received authentication code: " + recv_mac);
            if(mac.equals(recv_mac)){
			    System.out.println("Message received safely.");
            } else{
			    System.out.println("Message was NOT authenticated succesfully.");
            }

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
