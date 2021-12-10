/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.BibliotecaA;

import src.BibliotecaB.MiddlewareServidorB;
import src.BibliotecaB.RMIInterfaceB;
import src.BibliotecaC.MiddlewareServidorC;
import src.BibliotecaC.RMIInterfaceC;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Raikjars
 */
public class MiddlewareClienteA {

    public MiddlewareClienteA() {
    }
 
    public String PedirLibro(String nombre, String destino, FileWriter logs)throws MalformedURLException, RemoteException, NotBoundException, ParserConfigurationException, SAXException, IOException {
        if("B".equals(destino)) {
            RMIInterfaceB interfaceB = (RMIInterfaceB) Naming.lookup("rmi://"+src.Ips.B+"/BibliotecaB"); //ruta donde busca objeto
            MiddlewareServidorB mB = new MiddlewareServidorB();
            generateLogMCA(logs, "B: GetTitle " , nombre );
            return mB.getTitle(interfaceB, nombre, "A",logs); }
        else {
            RMIInterfaceC interfaceC = (RMIInterfaceC) Naming.lookup("rmi://"+src.Ips.C+"/BibliotecaC"); //ruta donde busca objeto
            MiddlewareServidorC mC = new MiddlewareServidorC();
            generateLogMCA(logs, "C: GetTitle " , nombre );
            return mC.getTitle(interfaceC, nombre, "A",logs); 
        }
    }
    
    public String PedirAutor(String nombre, String destino, FileWriter logs)throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        if("B".equals(destino)) {
            RMIInterfaceB interfaceB = (RMIInterfaceB) Naming.lookup("rmi://"+src.Ips.B+"/BibliotecaB"); //ruta donde busca objeto
            MiddlewareServidorB mB = new MiddlewareServidorB();
            generateLogMCA(logs, "B: GetAuthor " , nombre );
            return mB.getAuthor(interfaceB, nombre, "A",logs); }
        else {
            RMIInterfaceC interfaceC = (RMIInterfaceC) Naming.lookup("rmi://"+src.Ips.C+"/BibliotecaC"); //ruta donde busca objeto
            MiddlewareServidorC mC = new MiddlewareServidorC();
            generateLogMCA(logs, "C: GetAuthor " , nombre );
            return mC.getAuthor(interfaceC, nombre, "A",logs); 
        }
    }
    
    public static void generateLogMCA(FileWriter logs, String letter, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        logs.write("[" + actualDate + "]" + " Middleware Cliente A traduciendo peticion a Z39"+ " | ");
        System.out.println("[" + actualDate + "]" + " Middleware Cliente A traduciendo peticion a Z39");
        logs.write("[" + actualDate + "]" + " Peticion traducida. LLamando a Middleware de Servidor " + letter + data + " | ");
        System.out.println("[" + actualDate + "]" + " Peticion traducida. LLamando a Middleware de Servidor" + letter  + data );
    }
}
