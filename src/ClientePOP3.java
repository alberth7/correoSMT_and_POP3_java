import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientePOP3 {
	String host;
	int puerto;
	Socket cliente;
	PrintStream mensajeSolicitud;
	Scanner mensajeRespuesta;
	String user;
	String pass;
	
	public ClientePOP3(String h, int p) {
		puerto = p;
		host = h;
	}
	
	public void  leerMensaje() {
		System.out.println("***  leer correo por el protoclo POP3 ****");
		try {
			
			Scanner leer = new Scanner(System.in);
			cliente = new Socket(host, puerto);
			
			mensajeRespuesta = new Scanner(cliente.getInputStream());
			mensajeSolicitud = new PrintStream(cliente.getOutputStream());
			
			System.out.println(mensajeRespuesta.nextLine());
			
			boolean sw = true;
			while(sw) {
			System.out.println("usuario: ");
			user = leer.nextLine();
			
			System.out.println("password: ");
			pass = leer.nextLine();
			
			mensajeSolicitud.println("user " + user);
			System.out.println(mensajeRespuesta.nextLine());

			mensajeSolicitud.println("pass " + pass);
			
			//System.out.println(mensajeRespuesta.nextLine());
			
			String res[] = mensajeRespuesta.nextLine().split(" ");
			if(res[0].equals("+OK")) {
					
				mensajeSolicitud.println("list");
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				
				System.out.println("que mensaje desea leer:");
				String select =leer.nextLine();
				mensajeSolicitud.println("retr " + select);
				
				
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());
				System.out.println(mensajeRespuesta.nextLine());

				mensajeSolicitud.println("quit ");
				System.out.println("proceso de lectura de mesaje finalizado");
				sw=false;
				
			}else {
				System.out.println("\n....Error al ingresar al servidor, intente de nuevo");
				System.out.println();
			}
		
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ClientePOP3 cp = new ClientePOP3("192.168.17.30", 110);
		cp.leerMensaje();
	}
}
