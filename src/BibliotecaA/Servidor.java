package src.BibliotecaA;

import src.xml.Buscar;
import java.rmi.Naming;
import java.io.IOException;
import java.rmi.RemoteException;
import org.xml.sax.SAXException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.xml.parsers.ParserConfigurationException;

public class Servidor extends UnicastRemoteObject implements RMIInterfaceA {   
	private static final long serialVersionUID = 1L; 
    public Servidor() throws RemoteException {
        super(); 
    }

    @Override
    public String PedirLibro(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException {
        Buscar b = new Buscar();
        return "(A)" + b.RecorreXMLporTitulo(name, "A");
    }

    @Override
    public String PedirAutor(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException  {
        Buscar b = new Buscar();
        return "(A)" + b.RecorreXMLporAutor(name, "A");
    }
    
    public static void main(String[] args){
        try {
					LocateRegistry.createRegistry(5091);
					Naming.rebind("rmi://localhost:5091/BibliotecaA", new Servidor());      //ruta donde sube objeto remoto e instancia del mismo      
          System.err.println("Server BibliotecaA running");   
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
