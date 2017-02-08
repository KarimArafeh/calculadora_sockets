import HiloPeticion.HiloPeticion;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by y2793623b on 08/02/17.
 */
public class Servidor {


    public static void main(String[] args) {
        try {
            System.out.println("Creando servidor");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizar el Binding ");

            InetSocketAddress addres= new InetSocketAddress("localhost",5555);

            serverSocket.bind(addres);

            System.out.println("Aceptando conexiones");

            do {
                Socket newSocket = serverSocket.accept();

                HiloPeticion hp = new HiloPeticion(newSocket);
                hp.start();
                System.out.println("Conexion recibida");
            }while(true);

            //serverSocket.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }


}
