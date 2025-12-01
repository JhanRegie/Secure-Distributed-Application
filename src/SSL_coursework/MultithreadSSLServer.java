package SSL_coursework;


import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class MultithreadSSLServer extends Thread {
	private String message; //client message
	SSLSocket sslsocket;
	BufferedReader in;
	
	public MultithreadSSLServer(SSLSocket s) {
		this.sslsocket = s;
	}
	
	
	/*
	 * Tasks
	 * 	Threads
	 * 	Firewall
	 * 	Run app across two machines
	 * 
	 */
	
	
	@Override
	public void run() {
        PrintWriter out = null; 
        BufferedReader in = null; 
        
        try { 
//          Sends message connected to this socket  
        	// get the OutputStream of client 
        	out = new PrintWriter(sslsocket.getOutputStream(), true); 
        	
//        	Reads text and listens for text connected to this socket
              // get the InputStream of client 
            in = new BufferedReader( new InputStreamReader(sslsocket.getInputStream())); 
            
//          List of departments contact details, phone number and email of department
            String[] departments = {
            		"0121 204 4007 and thehub@aston.ac.uk",
            		"0121 204 4976 and safety@aston.ac.uk",
            		"0121 204 4888 and international@aston.ac.uk",
            		"0121 204 4525 and library@aston.ac.uk",
            		"0121 204 3445 and isa_helpdesk@aston.ac.uk"
            };


//          Read input from client
//        	Read incoming messages
            while ((message = in.readLine()) != null) {
            	
//            	Disconnect client connection
            	if(message.equals("exit") || message.equals("close")) {
            		out.println("Disconnecting.. ");
            		sslsocket.close();
            	}
            	
//    	    	Return contact details of department based on clientMessage
            	switch(message) {
            	case "1":
            		out.println(departments[0]);
            		break;
            	case "2":
            		out.println(departments[1]);
            		break;
            	case "3":
            		out.println(departments[2]);
            		break;
            	case "4":
            		out.println(departments[3]);
            		break;
            	case "5":
            		out.println(departments[4]);
            		break;
            	default:
            		out.println("Department not recognised");
            	}
//            	Display current running thread
            	System.out.println( Thread.currentThread().getName() + ": " + sslsocket.getInetAddress() + ":" + sslsocket.getPort());
                System.out.println("recv:" + message); //Display received message
                
//              Display message of receive new connections
                if(sslsocket.isClosed()) {
                	System.out.println("Client:" + sslsocket.getInetAddress() 
                	+ " Port:" + sslsocket.getPort() + " " + Thread.currentThread().getName() 
                	+ " Disconnected ");
                }
            }
//        Display Error message    
        } catch(IOException e) {
        	e.printStackTrace();
        }
	    	

	}

}