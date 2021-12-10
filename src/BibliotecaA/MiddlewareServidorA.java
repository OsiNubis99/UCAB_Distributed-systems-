/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.BibliotecaA;

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
public class MiddlewareServidorA {
    
    public MiddlewareServidorA() {
    }
       
    public String getAuthor(RMIInterfaceA interfaceA, String nombre, String origen, FileWriter logs)throws RemoteException, ParserConfigurationException, SAXException, IOException, NotBoundException{
        //ESCRIBIENDO LOGS
        if("B".equals(origen)) {
            generateLogMSA(logs, "B", " Pedir Autor: " , nombre );
        }
        else {
            generateLogMSA(logs, "C", " Pedir Autor: " , nombre );
        }
        //FIN ESCRITURA
        
        //LLAMADA A METODO DESDE OBJETO REMOTO
        return interfaceA.PedirAutor(nombre);
    }
    
    public String getTitle(RMIInterfaceA interfaceA, String nombre, String origen, FileWriter logs)throws RemoteException, ParserConfigurationException, SAXException, IOException, NotBoundException{
        //ESCRIBIENDO LOGS
        if("B".equals(origen)) {
            generateLogMSA(logs, "B", " Pedir Libro: " , nombre );
        }
        else {
            generateLogMSA(logs, "C", " Pedir Libro: " , nombre );
        }
        //FIN ESCRITURA
        
        //LLAMADA A METODO DESDE OBJETO REMOTO
        return interfaceA.PedirLibro(nombre);
    }
    
    public static void generateLogMSA(FileWriter logs, String type ,String letter, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        logs.write(actualDate + " Peticion de Biblioteca " + letter + " | Middleware Servidor A traduciendo peticion de Z39 a lenguaje de Biblioteca A"+ " | ");
        System.out.println(actualDate + " Peticion de Biblioteca " + letter + " | Middleware Servidor A traduciendo peticion de Z39 a lenguaje de Biblioteca A");
        
        logs.write(actualDate + " Peticion de Biblioteca " + letter + " | Peticion traducida. Ejecutando metodo de Servidor "  + type + data + " | ");
        System.out.println(actualDate + " Peticion de Biblioteca " + letter + " | Peticion traducida. Ejecutando metodo de Servidor " + type + data);
        
    }
    
}
