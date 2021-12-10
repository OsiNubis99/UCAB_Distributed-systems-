package src.BibliotecaB;

import src.BibliotecaA.MiddlewareServidorA;
import src.BibliotecaA.RMIInterfaceA;
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

public class MiddlewareClienteB {
    
    public MiddlewareClienteB() {
    }
 
    public String BuscarTitulo(String nombre, String destino, FileWriter logs)throws MalformedURLException, RemoteException, NotBoundException, ParserConfigurationException, SAXException, IOException {
        if("A".equals(destino)) {
            RMIInterfaceA interfaceA = (RMIInterfaceA) Naming.lookup("rmi://"+src.Ips.A+"/BibliotecaA"); //ruta donde busca objeto
            MiddlewareServidorA mA = new MiddlewareServidorA();
            generateLogMCA(logs, "A: GetTitle " , nombre );
            return mA.getTitle(interfaceA, nombre, "A",logs); }
        else {
            RMIInterfaceC interfaceC = (RMIInterfaceC) Naming.lookup("rmi://"+src.Ips.C+"/BibliotecaC"); //ruta donde busca objeto
            MiddlewareServidorC mC = new MiddlewareServidorC();
            generateLogMCA(logs, "C: GetTitle " , nombre );
            return mC.getTitle(interfaceC, nombre, "A",logs); 
        }
    }
    
    public String BuscarAutor(String nombre, String destino, FileWriter logs)throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        if("A".equals(destino)) {
            RMIInterfaceA interfaceA = (RMIInterfaceA) Naming.lookup("rmi://"+src.Ips.A+"/BibliotecaA"); //ruta donde busca objeto
            MiddlewareServidorA mA = new MiddlewareServidorA();
            generateLogMCA(logs, "A: GetAuthor " , nombre );
            return mA.getAuthor(interfaceA, nombre, "A",logs); }
        else {
            RMIInterfaceC interfaceC = (RMIInterfaceC) Naming.lookup("rmi://"+src.Ips.C+"/BibliotecaC"); //ruta donde busca objeto
            MiddlewareServidorC mC = new MiddlewareServidorC();
            generateLogMCA(logs, "C: GetAuthor " , nombre );
            return mC.getAuthor(interfaceC, nombre, "A",logs); 
        }
    }
    
    public static void generateLogMCA(FileWriter logs, String letter, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        logs.write("\n[" + actualDate + "] Middleware Cliente B traduciendo peticion a Z39 | ");
					logs.flush();
        System.out.println("[" + actualDate + "] Middleware Cliente B traduciendo peticion a Z39");
        logs.write("\n[" + actualDate + "] Peticion traducida. LLamando a Middleware de Servidor " + letter + data + " | ");
					logs.flush();
        System.out.println("[" + actualDate + "] Peticion traducida. LLamando a Middleware de Servidor" + letter  + data );
    }
}
