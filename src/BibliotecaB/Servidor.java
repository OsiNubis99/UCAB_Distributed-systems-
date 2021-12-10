package src.BibliotecaB;

import src.xml.Buscar;
import java.rmi.Naming;
import java.io.IOException;
import java.rmi.RemoteException;
import org.xml.sax.SAXException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.xml.parsers.ParserConfigurationException;

public class Servidor extends UnicastRemoteObject implements RMIInterfaceB {    
    public Servidor() throws RemoteException {
        super(); 
    }

    @Override
    public String BuscarTitulo(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException {
        Buscar b = new Buscar();
        return "(B)" + b.RecorreXMLporTitulo(name, "B");
    }

    @Override
    public String BuscarAutor(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException {
        Buscar b = new Buscar();
        return "(B)" + b.RecorreXMLporAutor(name, "B");
    }
    
    public static void main(String[] args){
        try {
					LocateRegistry.createRegistry(5092);
					Naming.rebind("//localhost:5092/BibliotecaB", new Servidor());      //ruta donde sube objeto remoto e instancia del mismo      
          System.err.println("Server BibliotecaB running");   
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
