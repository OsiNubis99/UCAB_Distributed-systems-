package src.BibliotecaC;

import src.BibliotecaA.MiddlewareClienteA;
import src.BibliotecaA.RMIInterfaceA;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Cliente {
    private static RMIInterfaceC interfaceC;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ParserConfigurationException, SAXException, IOException {
        MiddlewareClienteC mC = new MiddlewareClienteC();
        FileWriter logs = new FileWriter("logsC.txt");
        while(true) {
            System.out.println("\nMENU DE BIBLIOTECA C\n\n");
            System.out.println("Seleccione la biblioteca en la que desea buscar:\n");
            System.out.println("[1] Biblioteca C\n");
            System.out.println("[2] Biblioteca B\n");
            System.out.println("[3] Biblioteca A\n");
            System.out.println("[4] Salir\n");
            System.out.print("Selecciona tu opción: ");
            Scanner myObj = new Scanner(System.in);
            int option = myObj.nextInt();
            System.out.println("\n\nMENU DE BIBLIOTECA C\n\n");
            System.out.println("Seleccione el tipo de busqueda que desea hacer:\n");
            System.out.println("[1] Encontrar Volumen\n");
            System.out.println("[2] Encontrar Autor\n");
            System.out.print("Selecciona tu opción: ");
            Scanner myObj1 = new Scanner(System.in);
            int search = myObj1.nextInt();
            System.out.print("\n\nEscribe tu busqueda: ");
            Scanner myObj2 = new Scanner(System.in);
            String data = myObj2.nextLine();
            
            switch(option) {
                case 1:
                    interfaceC = (RMIInterfaceC) Naming.lookup("rmi://"+src.Ips.C+"/BibliotecaC");
                    switch(search) {
                        case 1: 
                            generateLog(logs, " Peticion del Cliente C hacia su Servidor | Encontrar Volumen " , data );
                            String libro = interfaceC.EncontrarVol(data ); 
                            System.out.println(libro);
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                            break;
                        case 2:
                            generateLog(logs, " Peticion del Cliente C hacia su Servido | Encontrar Autor " , data );
                            String author = interfaceC.EncontrarAutor(data); 
                            System.out.println(author);
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                            break;
                    }
                    break;
                case 2:
                        switch(search) {
                            case 1:
                                generateLog(logs, " Peticion de Cliente C hacia Servidor B | Encontrar Volumen " , data );
                                String libro = mC.EncontrarVol(data, "B", logs);
                                System.out.println(libro);
                                generateLog(logs, " Peticion recibida correctamente | Respuesta: " , data );
                                break;
                            case 2:
                                generateLog(logs, " Peticion de Cliente C hacia Servidor B | Encontrar Autor " , data );
                                String author = mC.EncontrarAutor(data, "B", logs);
                                System.out.println(author);
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                break;
                        }
                        break;
                case 3:
                    switch(search) {
                            case 1:
                                generateLog(logs, " Peticion de Cliente C hacia Servidor A | Encontrar Volumen " , data );
                                String libro = mC.EncontrarVol(data, "A", logs);
                                System.out.println(libro);
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                break;
                            case 2:
                                generateLog(logs, " Peticion de Cliente C hacia Servidor A | Encontrar Autor " , data );
                                String author = mC.EncontrarAutor(data, "A", logs);
                                System.out.println(author);
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                break;
                    }
                    break;
                case 4:
                    logs.close();
                    break;
            }
        }
    }
    
    public static void generateLog(FileWriter logs, String text, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        System.out.println("[" + actualDate + "]" + text + data);
        logs.write("\n[" + actualDate + "]" + text + data);
					logs.flush();
    }
}
