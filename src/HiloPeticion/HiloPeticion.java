package HiloPeticion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.DoubleSummaryStatistics;

/**
 * Created by y2793623b on 08/02/17.
 */
public class HiloPeticion extends Thread{

    private Socket newSocket;


    public HiloPeticion(Socket newSocket) {
        this.newSocket = newSocket;
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            is = newSocket.getInputStream();

            OutputStream os = newSocket.getOutputStream();

            byte[] mensaje = new byte[50];
            is.read(mensaje);

            String[] oper = new String(mensaje).split(" ");

            switch (oper[1].charAt(0))
            {
                case '+':
                    System.out.println("resultat : ");
                    System.out.println(Double.valueOf(oper[0])+Double.valueOf(oper[2]));
                    break;
                case '-':
                    System.out.println("resultat : ");
                    System.out.println(Double.valueOf(oper[0]) - Double.valueOf(oper[2]));
                    break;
                case '*':
                    System.out.println("resultat : ");
                    System.out.println(Double.valueOf(oper[0]) * Double.valueOf(oper[2]));
                    break;
                case '/':
                    System.out.println("resultat : ");
                    System.out.println(Double.valueOf(oper[0]) / Double.valueOf(oper[2]));
                    break;
            }

            //System.out.println("Mensaje recibido : "+ new String (mensaje));

            //System.out.println("Cerrando el socket");

            newSocket.close();

            //System.out.println("Cerrando el socket servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
