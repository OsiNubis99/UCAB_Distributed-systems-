package src.BibliotecaC;

import src.xml.Buscar;
import java.rmi.Naming;
import java.io.IOException;
import java.rmi.RemoteException;
import org.xml.sax.SAXException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.xml.parsers.ParserConfigurationException;

public class Servidor extends UnicastRemoteObject implements RMIInterfaceC {    
    public Servidor() throws RemoteException {
        super(); 
    }

    @Override
    public String EncontrarVol(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException{
        Buscar b = new Buscar();
        return "(C)" + b.RecorreXMLporTitulo(name, "C");
    }

    @Override
    public String EncontrarAutor(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException {
        Buscar b = new Buscar();
        return "(C)" + b.RecorreXMLporAutor(name, "C");
    }
    
    public static void main(String[] args){
        try {
					LocateRegistry.createRegistry(5093);
					Naming.rebind("rmi://localhost:5093/BibliotecaC", new Servidor());      //ruta donde sube objeto remoto e instancia del mismo      
          System.err.println("Server BibliotecaC running");   
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

