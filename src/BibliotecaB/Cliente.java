package src.BibliotecaB;

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
    
    private static RMIInterfaceB interfaceB;
    
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ParserConfigurationException, SAXException, IOException {
        MiddlewareClienteB mB = new MiddlewareClienteB();
        FileWriter logs = new FileWriter("logsB.txt");
        while(true) {
            System.out.println("\nMENU DE BIBLIOTECA B\n\n");
            System.out.println("Seleccione la biblioteca en la que desea buscar:\n");
            System.out.println("[1] Biblioteca B\n");
            System.out.println("[2] Biblioteca A\n");
            System.out.println("[3] Biblioteca C\n");
            System.out.println("[4] Salir\n");
            System.out.print("Selecciona tu opción: ");
            Scanner myObj = new Scanner(System.in);
            int option = myObj.nextInt();
            System.out.println("\n\nMENU DE BIBLIOTECA B\n\n");
            System.out.println("Seleccione el tipo de busqueda que desea hacer:\n");
            System.out.println("[1] Buscar por titulo\n");
            System.out.println("[2] Buscar por autor\n");
            System.out.print("Selecciona tu opción: ");
            Scanner myObj1 = new Scanner(System.in);
            int search = myObj1.nextInt();
            System.out.print("\n\nEscribe tu busqueda: ");
            Scanner myObj2 = new Scanner(System.in);
            String data = myObj2.nextLine();
            switch(option) {
                case 1:
                    interfaceB = (RMIInterfaceB) Naming.lookup("rmi://"+src.Ips.B+"/BibliotecaB"); //ruta donde busca objeto
                    switch(search) {
                        case 1:
                            generateLog(logs, " Peticion del Cliente B hacia su Servidor | Buscar Titulo " , data );
                            String libro = interfaceB.BuscarTitulo(data ); 
                            System.out.println(libro);
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                            break;
                        case 2:
                            generateLog(logs, " Peticion del Cliente B hacia su Servido | Buscar Autor " , data );
                            String author = interfaceB.BuscarAutor(data); 
                            System.out.println(author);
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                            break;
                    }
                    break;
                case 2:
                        switch(search) {
                            case 1:
                                generateLog(logs, " Peticion de Cliente B hacia Servidor A | Buscar Titulo " , data );
                                String libro = mB.BuscarTitulo(data, "A", logs);
                                System.out.println(libro);
                                generateLog(logs, " Peticion recibida correctamente | Respuesta: " , data );
                                break;
                            case 2:
                                generateLog(logs, " Peticion de Cliente B hacia Servidor A | Buscar Autor " , data );
                                String author = mB.BuscarAutor(data, "A", logs);
                                System.out.println(author);
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                break;
                        }
                        break;
                case 3:
                    switch(search) {
                            case 1:
                                generateLog(logs, " Peticion de Cliente B hacia Servidor C | Buscar Titulo " , data );
                                String libro = mB.BuscarTitulo(data, "C", logs);
                                System.out.println(libro);
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                break;
                            case 2:
                                generateLog(logs, " Peticion de Cliente B hacia Servidor C | Buscar Autor " , data );
                                String author = mB.BuscarAutor(data, "C", logs);
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
