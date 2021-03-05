package cliente;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//TAREAS:
//TCP X
//HASH X
//THREADS
public class Cliente extends Thread {

	
	private static int ID;
	private Socket socket;


	public Cliente(int id, Socket psocket)  {
		ID=id;
		socket = psocket;
	}

	// -----------------------------------------------------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------------------------------------------------

	/*
	 * Metodo que activa el thread para que empieze a realizar comparaciones
	 */
	public void run() {
		
		DataOutputStream mensaje;

		try {
			System.out.println("NACI "+ID);
	
			
			mensaje = new DataOutputStream(socket.getOutputStream());
			
			
			mensaje.writeUTF("Hola soy un cliente"+ID);
			
			

		} catch (UnknownHostException e) {
			System.out.println("El host no existe o no está activo.");
		} catch (IOException e) {
			System.out.println("Error de entrada/salida."+e);
		}
	}



	/**
	 * Metodo generar_codigo. Recibe una cadena de texto y una cadena con el nombre
	 * de un algoritmo. Retorna el codigo criptogrqfico de hash correspondiente.}
	 * <b>pre: </b> El algoritmo debe pertenecer a [ MD5, SHA256, SHA384, SHA512]
	 * <b>pre: </b> El texto debe ser de maximo 7 letras y deben estar entre a y z (27 letras)
	 * 
	 * @param texto     que se decea cifrar por hash correspondiente
	 * @param algoritmo con el que se desea cifrar
	 * @return Retorna el codigo criptografico de hash correspondiente.
	 */
	public static String generar_codigo(String texto) {
		String RTA = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(texto.getBytes());

			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < bytes.length; i++) {

				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			RTA = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return RTA;

	}






}
