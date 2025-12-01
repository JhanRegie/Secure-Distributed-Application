package SSL_coursework;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;


public class SSL_astonContactLocatorClient {
	public static void main (String[] args) throws IOException{
		
//		Port to connect
		int portNumber = 17777;  
//		Server host name to connect
		String hostName = "127.0.0.1";
		
//		TrustStore for client's list of trusted certificate file path location and password
		System.setProperty("javax.net.ssl.trustStore", "C:\\snslab\\coursework.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "999999");

//		Factory for creating SSL sockets
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
//		create SSL Socket
		SSLSocket socket = (SSLSocket) factory.createSocket(hostName, portNumber);
		
//		Print message of connecting
		System.out.println("Connecting");
		
//		Client initiates connection to server
		socket.startHandshake();
		
		if(socket.isConnected()) {
			System.out.println("SSLSocket and SSLServerSocket connection established");
		}
		
//		Sends message to connected SSL serverSocket
       	PrintWriter out = new PrintWriter( socket.getOutputStream(), true );
//      Reads text and listen for incoming messages connected
        BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
//      reads text input from user on console
        BufferedReader userInput = new BufferedReader( new InputStreamReader(System.in) );
        
        
        while( true ) {
//        	Display List of department's contact details to client's console 
			System.out.println(
					"Select a number from the list of departments\n" + 
					"1. Student Support Services Contact\n"
					+ "2. Health and Safety Contact\n"
					+ "3. International Office Contact\n"
					+ "4. Library and Information Services Contact\n"
					+ "5. IT Services Contact"
					);
			
			
//			Client message
            String message;
        	System.out.print("send:");
//        	Read text's from console
        	message = userInput.readLine();
        	out.println(message); //Send message to server
        	
        	
//        	Receive response from server
        	String serverMessage;
//        	read text from server
        	serverMessage = in.readLine();
        	System.out.println(serverMessage); //Print server's message
        	
//        	Close socket connection
        	if(message.equals("exit") || message.equals("close")) {
        		System.out.println("SSLSocket Disconnected");
        		socket.close();
        		System.exit(0);
        	}
        	
        }
	}
}
