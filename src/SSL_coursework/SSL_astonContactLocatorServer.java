package SSL_coursework;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;


public class SSL_astonContactLocatorServer {
	public static void main (String[] args) throws IOException{
				
		
//		keyStore for the server's certificate file path location and password
		System.setProperty( "javax.net.ssl.keyStore", "C:\\snslab\\coursework.jks" );
		System.setProperty( "javax.net.ssl.keyStorePassword", "999999" );
		
//		Factory for creating SSLServer sockets
        SSLServerSocketFactory factory = (SSLServerSocketFactory) 
        SSLServerSocketFactory.getDefault();
//      Create SSL ServerSocket
        SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(17777);
        
//      Server awaits for a connection
        System.out.println("Waiting for a Connection");

//      Run Program
        while (true) {
//          Create SSL Socket and ServerSocket listens and accept connection on the SSL socket
        	SSLSocket sslSocket = (SSLSocket) serverSocket.accept();
        	
//        	Print if established connection is made 
        	if(sslSocket.isConnected()) {
            	System.out.println("Connected to " + sslSocket.getInetAddress());
            	System.out.println("SSLServer Connection established on Port:" + sslSocket.getPort());
        	}
        	
//        	Create MultithreadSSLServer object and start execution of thread
        	MultithreadSSLServer msslserver = new MultithreadSSLServer(sslSocket);
        	msslserver.start();
        	
        	  	
        }    
     }      
}

