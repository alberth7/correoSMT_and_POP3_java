import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSMPT {
	String host;
	int puerto;
	Socket cliente;
	PrintStream mensajeSolicitud;
	Scanner mensajeRespuesta;
	String hostCorreo;
	String mailFrom;
	String rcptTo;	
	String subject;
	String mensaje;

public ClienteSMPT(String h, int p, String hc) {
	host = h;
	puerto = p;
	hostCorreo = hc;
}		

public void enviarCorreo() {
	try {
		Scanner leer = new Scanner(System.in);
		cliente = new Socket(host, puerto);
		
		mensajeRespuesta = new Scanner(cliente.getInputStream());
		mensajeSolicitud = new PrintStream(cliente.getOutputStream());
		
		System.out.println(mensajeRespuesta.nextLine());
		
		boolean sw = true;
		while(sw) {
			System.out.println("Enviar correo de: ");
			mailFrom = leer.nextLine();
			mensajeSolicitud.println("mail from: " + mailFrom + hostCorreo);
			
			//System.out.println(mensajeRespuesta.nextLine());
			String res[] = mensajeRespuesta.nextLine().split(" ");
			
			if(res[0].equals("250")) {
				//System.out.println("validacion user2");
				while(sw) {
					System.out.println("Enviar correo a: ");
					rcptTo = leer.nextLine();
					mensajeSolicitud.println("rcpt to: " + rcptTo + hostCorreo);
					res = mensajeRespuesta.nextLine().split(" ");
					
					if(res[0].equals("250")) {
						//System.out.println("usuarios validaldos");
						
						mensajeSolicitud.println("data");
					
						System.out.println("---- Escribe el Mensaje ---- ");
						System.out.println("subject:");
						subject = leer.nextLine();
						mensajeSolicitud.println("subject: " + subject);
						
						System.out.println("mensaje: ");
						mensaje = leer.nextLine();
						mensajeSolicitud.println(mensaje);
						
						mensajeSolicitud.println(".");
						
						System.out.println(mensajeRespuesta.nextLine());
						
						mensajeSolicitud.println("quit");
						
						System.out.println(mensajeRespuesta.nextLine());
						
						System.out.println("finalizo el proceso.de envio de mensaje..");
						sw =false;
						finalizar();
					}else {
						System.out.println("\n....Error al ingresar al servidor, intente de nuevo");
						System.out.println();
					}
						
					
					
				}
				
			}else {
				System.out.println("\n....Error al ingresar al servidor, intente de nuevo");
				System.out.println();
			}
					
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private void finalizar() {
	try {
		cliente.close();
		mensajeRespuesta.close();
		mensajeSolicitud.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public static void main(String[] args) {
	ClienteSMPT C = new ClienteSMPT("192.168.17.30",25,"@correo.alberth.bo");		
	C.enviarCorreo();
}


}
