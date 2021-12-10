/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Raikjars
 */
public class Buscar {
    
    public Buscar() {
    }
    
    public static String RecorreXMLporTitulo (String titulo, String biblioteca) throws ParserConfigurationException, SAXException, IOException{  

        File archivo;

        if("A".equals(biblioteca)) {
            archivo = new File("biblioteca A.xml"); }
        else if("B".equals(biblioteca)) {
            archivo = new File("biblioteca B.xml");
        } else {
            archivo = new File("biblioteca C.xml");
        }
        System.out.println("entreeeeeee");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);

        document.getDocumentElement().normalize();
        NodeList listaLibros = document.getElementsByTagName("book");

        Node nodo;
        for(int i = 0 ; i < listaLibros.getLength() ; i++) {
            nodo = listaLibros.item(i);
            NodeList listaCaracteristicas = nodo.getChildNodes();
            Node caracteristica;
            for(int z=0; z<listaCaracteristicas.getLength(); z++){
                caracteristica = listaCaracteristicas.item(z);
                
                if(caracteristica.getNodeName().equals("libro") && caracteristica.getTextContent().equals(titulo)){
                    
                    for (int s=0; s<listaCaracteristicas.getLength(); s++){
                        caracteristica = listaCaracteristicas.item(s);

                        if(caracteristica.getNodeName().equals("autor")){
                        //JOptionPane.showMessageDialog(null, "Se encontro el libro " + titulo + " Autor: " + caracteristica.getTextContent());

                        if("A".equals(biblioteca)) {
                            return " Libro: " + titulo + " " +caracteristica.getTextContent();
                        } else if ("B".equals(biblioteca)) {
                            return " Titulo: " + titulo + " " +caracteristica.getTextContent();
                        } else {
                            return " Vol: " + titulo + " " + caracteristica.getTextContent();
                        }

                        }
                    }

                }
            }
         }
         return "No se encontro el Libro";
           
    }
    
    public static String RecorreXMLporAutor (String autor, String biblioteca) throws ParserConfigurationException, SAXException, IOException{  
        File archivo;
           
        if("A".equals(biblioteca)) {
            archivo = new File("BibliotecaAXML.xml"); }
        else if("B".equals(biblioteca)) {
            archivo = new File("BibliotecaBXML.xml");
        } else {
            archivo = new File("BibliotecaCXML.xml");
        }
        
        List<String> listaLibroAutor = new ArrayList<String>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);

        document.getDocumentElement().normalize();
        NodeList listaLibros = document.getElementsByTagName("LIBRO");

        Node nodo;
        for(int i = 0 ; i < listaLibros.getLength() ; i++) {
            nodo = listaLibros.item(i);
            NodeList listaCaracteristicas = nodo.getChildNodes();
            Node caracteristica;
            for(int z=0; z<listaCaracteristicas.getLength(); z++){
                caracteristica = listaCaracteristicas.item(z);
            if(caracteristica.getNodeName().equals("AUTOR") && caracteristica.getTextContent().equals(autor)){
                for(int x=0; x < listaCaracteristicas.getLength();x++){
                    caracteristica = listaCaracteristicas.item(x);
                    if(caracteristica.getNodeName().equals("TITULO")){
                        listaLibroAutor.add(caracteristica.getTextContent());
                    }
                }
               /* JOptionPane.showMessageDialog(null, "Se encontro el autor " + autor);
                return "Autor: " + autor;*/
            }
            }
        }
        
        if (listaLibroAutor.isEmpty()){
            return "No se encontro el Autor";  
        }
        String libros = "";  
        for(int i = 0; i < listaLibroAutor.size(); i++){
            libros = libros + " - " + listaLibroAutor.get(i);
        }
             
             
        if("A".equals(biblioteca)) {
            return " Libro: " + autor + " " +libros;
        } else if ("B".equals(biblioteca)) {
            return " Titulo: " + autor + " " +libros;
        } else {
            return " Vol: " + autor + " " + libros;
        }
    }
    
}
