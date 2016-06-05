import java.io.*;
import java.net.*;
import java.net.InetAddress;


class Client {

    public static void main(String args[]) {

         Socket sock=null;
         DataInputStream dis=null;
         PrintStream ps=null;

         try {
 
			System.out.println("Connecting...");
			InetAddress ip =InetAddress.getByName("localhost");

            sock= new Socket(ip, Server.PORT); ps= new 
            PrintStream(sock.getOutputStream());
			ps.println("Init message from client");

    		BufferedReader is = new BufferedReader(new 
                    InputStreamReader(sock.getInputStream()));

            System.out.println(is.readLine());

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
