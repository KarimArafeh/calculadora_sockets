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
        Scanner scr = new Scanner(System.in);
        System.out.println("numero 1 :");
        String num1 =scr.nextLine();
        System.out.println("numero 2 :");
        String num2 = scr.nextLine();

        System.out.println("Escribe el numero de la operacion que quieres hacer :");
        System.out.println("1) sumar");
        System.out.println("2) restar");
        System.out.println("3) multiplicar");
        System.out.println("4) dividir");

        int option = scr.nextInt();

        String operacion = null;
        switch (option)
        {
            case 1:
                operacion = num1 + " " + "+" + " " + num2;
                break;
            case 2:
                operacion = num1 + " " + "-" + " " + num2;
                break;
            case 3:
                operacion = num1 + " " + "*" + " " + num2;
                break;
            case 4:
                operacion = num1 + " " + "/" + " " + num2;
                break;
            default:
                break;
        }



        try {

            //Creando un cliente
            Socket cliente = new Socket();
            DatagramSocket udp = new DatagramSocket();

            //Creando una conecxion
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            cliente.connect(addr);
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();

            String mensaje = operacion;
            //enviando
            os.write(mensaje.getBytes());
            //operacion enviada

            cliente.close();

            System.out.println("se ha guardado en el fichero (result.xml)");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
