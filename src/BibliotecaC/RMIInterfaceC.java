/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.BibliotecaC;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Raikjars
 */
public interface RMIInterfaceC extends Remote {
    
    public String EncontrarVol(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException;
     
    public String EncontrarAutor(String name) throws RemoteException, ParserConfigurationException, SAXException, IOException;
}
