import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by y2793623b on 08/02/17.
 */
public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la Operacion");
        String operacion=sc.nextLine();


        try {

            System.out.println("Creando un cliente");
            Socket cliente = new Socket();
            DatagramSocket udp = new DatagramSocket();

            System.out.println("Creando una conecxion");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            cliente.connect(addr);
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            String mensaje = operacion;
            System.out.println("Enviando : ");
            os.write(mensaje.getBytes());
            System.out.println("Operacion enviado");

            System.out.println("Creando socket del cliente");
            cliente.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
