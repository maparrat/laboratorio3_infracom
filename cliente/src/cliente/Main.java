package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	private static String HOST = "localhost";
	private static int PUERTO = 2017;
	
	public static void main(String args[]) throws InterruptedException {

	
		
		
		Socket socket;
		DataOutputStream mensaje;

		try {
			
			socket = new Socket(HOST, PUERTO);
		
			
			
			for (int i = 0; i < 25; i++) {
				Cliente thread_nuevo = new Cliente(i+1, socket);
				 
				thread_nuevo.start();
				thread_nuevo.join();

			}
			//Cerramos la conexión
			socket.close();

		} catch (UnknownHostException e) {
			System.out.println("El host no existe o no está activo.");
		} catch (IOException e) {
			System.out.println("Error de entrada/salida."+e);
		}

		
	}
}
