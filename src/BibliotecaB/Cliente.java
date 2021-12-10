/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.BibliotecaB;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import static javafx.application.Platform.exit;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Raikjars
 */
public class Cliente {
    
    private static RMIInterfaceB interfaceB;
    
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ParserConfigurationException, SAXException, IOException {
        
        MiddlewareClienteB mB = new MiddlewareClienteB(); //Se instacia objeto de clase Middleware para traducir peticiones
        //CREACION DEL ARCHIVO DE LOGS
        FileWriter logs = new FileWriter("C:\\Users\\Public\\Logs\\logsB.txt");
        
        
        while(true) {
            
            //SELECCION DE BIBLIOTECA
            System.out.println("\nMENU DE BIBLIOTECA B\n\n");
            System.out.println("Seleccione la biblioteca en la que desea buscar:\n");
            System.out.println("[1] Biblioteca B\n");
            System.out.println("[2] Biblioteca A\n");
            System.out.println("[3] Biblioteca C\n");
            System.out.println("[4] Salir\n");
            System.out.print("Selecciona tu opción: ");
            Scanner myObj = new Scanner(System.in);
            int option = myObj.nextInt();
            //SELECCION DEL TIPO DE BUSQUEDA
            System.out.println("\n\nMENU DE BIBLIOTECA B\n\n");
            System.out.println("Seleccione el tipo de busqueda que desea hacer:\n");
            System.out.println("[1] Buscar por titulo\n");
            System.out.println("[2] Buscar por autor\n");
            System.out.print("Selecciona tu opción: ");
            Scanner myObj1 = new Scanner(System.in);
            int search = myObj1.nextInt();
            
            //SELECCION DEL TEXTO A BUSCAR
            System.out.print("\n\nEscribe tu busqueda: ");
            Scanner myObj2 = new Scanner(System.in);
            String data = myObj2.nextLine();
            switch(option) {
                case 1:
                    //PETICIONES LOCALES A BIBLIOTECA A
               
                    interfaceB = (RMIInterfaceB) Naming.lookup("rmi://"+src.Ips.B+"/BibliotecaB"); //ruta donde busca objeto
                    switch(search) {
                        case 1: //SI SE PIDE POR TITULO
                            
                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion del Cliente B hacia su Servidor | Buscar Titulo " , data );

                            //LLAMA LOCALMENTE AL SERVIDOR DE LA BIBLIOTECA B
                            String libro = interfaceB.BuscarTitulo(data ); 
                            System.out.println(libro);
                            
                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );

                            break;
                        case 2://SI SE PIDE POR AUTOR

                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion del Cliente B hacia su Servido | Buscar Autor " , data );

                             //LLAMA LOCALMENTE AL SERVIDOR DE LA BIBLIOTECA B
                            String author = interfaceB.BuscarAutor(data); 
                            System.out.println(author);
                            
                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                            break;
                    }
                    break;
                case 2: //Entra por aqui si se quiere pedir libro a biblioteca A

                        //SI CLIENTE PIDE POR TITULO A BIBLIOTECA A
                        switch(search) {
                            case 1: //SI SE PIDE POR TITULO
                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion de Cliente B hacia Servidor A | Buscar Titulo " , data );

                                //Cliente A llama a medoto PedirLibro de su Middleware para que este lo traduzca a Z39
                                String libro = mB.BuscarTitulo(data, "A", logs);
                                System.out.println(libro);
                                
                                 //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion recibida correctamente | Respuesta: " , data );
                                break;
                            case 2:
                                
                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion de Cliente B hacia Servidor A | Buscar Autor " , data );

                                //Cliente A llama a medoto PedirAutor de su Middleware para que este lo traduzca a Z39
                                String author = mB.BuscarAutor(data, "A", logs);
                                System.out.println(author);

                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                break;
                        }
                        break;
                case 3:

                    //SI CLIENTE PIDE POR TITULO A BIBLIOTECA C
                    switch(search) {
                            case 1:
                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion de Cliente B hacia Servidor C | Buscar Titulo " , data );

                                String libro = mB.BuscarTitulo(data, "C", logs);
                                System.out.println(libro);

                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                
                                break;
                                
                            case 2: //SI CLIENTE PIDE POR AUTOR A BIBLIOTECA C
                                
                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion de Cliente B hacia Servidor C | Buscar Autor " , data );

                                //Cliente A llama a medoto PedirAutor de su Middleware para que este lo traduzca a Z39
                                String author = mB.BuscarAutor(data, "C", logs);
                                System.out.println(author); 

                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                
                                break;
                    }
                    break;
                case 4:
                    //CIERRA LA ESCRITURA DEL ARCHIVO DE LOGS
                    logs.close();
                    exit();
            } //FIN DEL SWITCH
        
        }//FIN DE WHILE 
        
 
    }//FIN DEL MAIN
    
    public static void generateLog(FileWriter logs, String text, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        System.out.println("[" + actualDate + "]" + text + data);
        logs.write("[" + actualDate + "]" + text + data);
    }
}
