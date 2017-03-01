package HiloPeticion;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

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

            byte[] mensaje = new byte[10];
            is.read(mensaje);

            String[] oper = new String(mensaje).split(" ");
            double resultado = 0;

            switch (oper[1].charAt(0))
            {
                case '+':
                    System.out.println("resultat : ");
                    resultado = Double.valueOf(oper[0]) + Double.valueOf(oper[2]);
                    break;
                case '-':
                    System.out.println("resultat : ");
                    resultado = Double.valueOf(oper[0]) - Double.valueOf(oper[2]);
                    break;
                case '*':
                    System.out.println("resultat : ");
                    resultado = Double.valueOf(oper[0]) * Double.valueOf(oper[2]);
                    break;
                case '/':
                    System.out.println("resultat : ");
                    resultado = Double.valueOf(oper[0]) / Double.valueOf(oper[2]);
                    break;
            }
            /*
            FileWriter file = new FileWriter("resultat");
            file.write(mensaje.toString());
            file.write(String.valueOf(resultado));
            */
            System.out.println(resultado);

            //crearXml(mensaje.toString(), resultado);
			crearXml(new String(mensaje), resultado);


            newSocket.close();

            //System.out.println("Cerrando el socket servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crearXml(String operacion, double resultado) {

        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null,"xml",null);

            //Creación de elementos
            //creamos el elemento principal Info
            Element info = document.createElement("Info");

            //creamos un nuevo elemento. info tiene data
            Element data = document.createElement("Date");
            //Ingresamos la info.
			Date fecha = new Date();
            Text valorData = document.createTextNode(String.valueOf(fecha));

            //creamos un nuevo elemento. info tiene operation
            Element operation = document.createElement("Operation");
            //Ingresamos la info.
            Text valorOperation = document.createTextNode(operacion);

            //creamos un nuevo elemento. info tiene resultat
            Element result = document.createElement("Result");
            //Ingresamos la info.
            Text valorResult = document.createTextNode(String.valueOf(resultado));

            //creamos un nuevo elemento. info tiene ip
            Element ip = document.createElement("Ip");
            //Ingresamos la info.
            Text valorIp = document.createTextNode("la ip");

            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0");
            //Añadimos la info al documento
            document.getDocumentElement().appendChild(info);
            //Añadimos el elemento hijo a la raíz
            info.appendChild(data);
            //Añadimos valor
            data.appendChild(valorData);
            //Añadimos el elemento hijo a la raíz
            info.appendChild(operation);
            //Añadimos valor
            operation.appendChild(valorOperation);
            //Añadimos el elemento hijo a la raíz
            info.appendChild(result);
            //Añadimos valor
            result.appendChild(valorResult);
            //Añadimos el elemento hijo a la raíz
            info.appendChild(ip);
            //Añadimos valor
            ip.appendChild(valorIp);

            // escribimos el contenido en un archivo .xml

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);

            StreamResult file = new StreamResult(new File("result.xml"));

            transformer.transform(source, file);

        }catch(Exception e){
            System.err.println("Error: "+e);
        }

    }

}
