/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.BibliotecaC;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Raikjars
 */
public class MiddlewareServidorC {
    
    public MiddlewareServidorC() {
    }
       
    public String getAuthor(RMIInterfaceC interfaceC, String nombre, String origen, FileWriter logs)throws RemoteException, ParserConfigurationException, SAXException, IOException, NotBoundException{
        //ESCRIBIENDO LOGS
        if("A".equals(origen)) {
            generateLogMSA(logs, "A", " Encontrar Autor: " , nombre );
        }
        else {
            generateLogMSA(logs, "B", " Encontrar Autor: " , nombre );
        }
        //FIN ESCRITURA
        
        //LLAMADA A METODO DESDE OBJETO REMOTO
        return interfaceC.EncontrarAutor(nombre);
    }
    
    public String getTitle(RMIInterfaceC interfaceC, String nombre, String origen, FileWriter logs)throws RemoteException, ParserConfigurationException, SAXException, IOException, NotBoundException{
        //ESCRIBIENDO LOGS
        if("A".equals(origen)) {
            generateLogMSA(logs, "A", " Encontrar Vol: " , nombre );
        }
        else {
            generateLogMSA(logs, "B", " Encontrar Vol: " , nombre );
        }
        //FIN ESCRITURA
        
        //LLAMADA A METODO DESDE OBJETO REMOTO
        return interfaceC.EncontrarVol(nombre);
    }
    
    public static void generateLogMSA(FileWriter logs, String type ,String letter, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        logs.write(actualDate + " Peticion de Biblioteca " + letter + " | Middleware Servidor B traduciendo peticion de Z39 a lenguaje de Biblioteca B"+ " | ");
        System.out.println(actualDate + " Peticion de Biblioteca " + letter + " | Middleware Servidor B traduciendo peticion de Z39 a lenguaje de Biblioteca B");
        
        logs.write(actualDate + " Peticion de Biblioteca " + letter + " | Peticion traducida. Ejecutando metodo de Servidor "  + type + data + " | ");
        System.out.println(actualDate + " Peticion de Biblioteca " + letter + " | Peticion traducida. Ejecutando metodo de Servidor " + type + data);
        
    }
}
