
package src.BibliotecaA;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.Icon;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Cliente {
    
    private static RMIInterfaceA interfaceA;
    
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, ParserConfigurationException, SAXException, IOException {
        MiddlewareClienteA mA = new MiddlewareClienteA();
        FileWriter logs = new FileWriter("C:\\Users\\Public\\Logs\\logsA.txt");
        while(true) {
            System.out.println("\nMENU DE BIBLIOTECA A\n\n");
            System.out.println("Seleccione la biblioteca en la que desea buscar:\n");
            System.out.println("[1] Biblioteca A\n");
            System.out.println("[2] Biblioteca B\n");
            System.out.println("[3] Biblioteca C\n");
            System.out.println("[4] Salir\n");
            System.out.print("Selecciona tu opción: ");
            Scanner myObj = new Scanner(System.in);
            int option = myObj.nextInt();
            if(option == 4){
                logs.close();
                break;
            }
            System.out.println("\n\nMENU DE BIBLIOTECA A\n\n");
            System.out.println("Seleccione el tipo de busqueda que desea hacer:\n");
            System.out.println("[1] Pedir libro\n");
            System.out.println("[2] Pedir Autor\n");
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
               
                    interfaceA = (RMIInterfaceA) Naming.lookup("rmi://"+src.Ips.A+"/BibliotecaA"); //ruta donde busca objeto
                    switch(search) {
                        case 1: //SI SE PIDE POR TITULO
                            
                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion del Cliente A hacia su Servidor | Pedir Libro " , data );

                            //LLAMA LOCALMENTE AL SERVIDOR DE LA BIBLIOTECA A
                            String libro = interfaceA.PedirLibro(data ); 
                            System.out.println(libro);
                            
                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );

                            break;
                        case 2://SI SE PIDE POR AUTOR

                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion del Cliente A hacia su Servido | Pedir Autor " , data );

                             //LLAMA LOCALMENTE AL SERVIDOR DE LA BIBLIOTECA A
                            String author = interfaceA.PedirAutor(data); 
                            System.out.println(author);
                            
                            //Imprimiendo traza y escribiendo logs en txt
                            generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                            break;
                    }
                    break;
                case 2: //Entra por aqui si se quiere pedir libro a biblioteca B

                        //SI CLIENTE PIDE POR TITULO A BIBLIOTECA B
                        switch(search) {
                            case 1: //SI SE PIDE POR TITULO
                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion de Cliente A hacia Servidor B | Pedir Libro " , data );

                                //Cliente A llama a medoto PedirLibro de su Middleware para que este lo traduzca a Z39
                                String libro = mA.PedirLibro(data, "B", logs);
                                System.out.println(libro);
                                
                                 //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion recibida correctamente | Respuesta: " , data );
                                break;
                            case 2:
                                
                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion de Cliente A hacia Servidor B | Pedir Autor " , data );

                                //Cliente A llama a medoto PedirAutor de su Middleware para que este lo traduzca a Z39
                                String author = mA.PedirAutor(data, "B", logs);
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
                                generateLog(logs, " Peticion de Cliente A hacia Servidor C | Pedir Libro " , data );

                                String libro = mA.PedirLibro(data, "C", logs);
                                System.out.println(libro);

                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                
                                break;
                                
                            case 2: //SI CLIENTE PIDE POR AUTOR A BIBLIOTECA C
                                
                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion de Cliente A hacia Servidor C | Pedir Autor " , data );

                                //Cliente A llama a medoto PedirAutor de su Middleware para que este lo traduzca a Z39
                                String author = mA.PedirAutor(data, "C", logs);
                                System.out.println(author); 

                                //Imprimiendo traza y escribiendo logs en txt
                                generateLog(logs, " Peticion recibida correctamente | Respuesta:  " , data );
                                
                                break;
                    }
                    break;
            } //FIN DEL SWITCH
        
        }//FIN DE WHILE 
        
 
    }//FIN DEL MAIN
    
    public static void generateLog(FileWriter logs, String text, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        System.out.println("[" + actualDate + "]" + text + data);
        logs.write("[" + actualDate + "]" + text + data);
    }
    
}
