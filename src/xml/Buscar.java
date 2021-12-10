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

public class Buscar {
    
    public Buscar() {
    }
    
		public static String RecorreXML (String nombre_archivo,String parametroA, String parametroB) throws ParserConfigurationException, SAXException, IOException{  
        File archivo = new File(nombre_archivo);
        System.out.println("Solicitud aceptada");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        document.getDocumentElement().normalize();
				NodeList listaCaracteristicas;
				NodeList listaCaracteristicas2;
        NodeList listaLibros = document.getElementsByTagName("book");
        Node nodo;
        String msg = ""; 
        for(int i = 0 ; i < listaLibros.getLength() ; i++) {
            nodo = listaLibros.item(i);
            listaCaracteristicas = nodo.getChildNodes();
            Node caracteristica;
            for(int z=0; z<listaCaracteristicas.getLength(); z++){
                caracteristica = listaCaracteristicas.item(z);
                if(caracteristica.getNodeName().equals(parametroA) && caracteristica.getTextContent().equals(parametroB)){
            			listaCaracteristicas2 = nodo.getChildNodes();
									for(int x=0; x<listaCaracteristicas.getLength(); x++){
									  System.out.println(listaCaracteristicas2.item(x).getTextContent());
									}
									msg +="\n"+parametroA + " " + listaCaracteristicas2.item(1).getTextContent() + " " + listaCaracteristicas2.item(3).getTextContent();
                }
            }
         }
        if (msg == ""){
            return " No se encontro el Autor";  
        }
				return msg;
    }

    public static String RecorreXMLporTitulo (String titulo, String biblioteca) throws ParserConfigurationException, SAXException, IOException{  
        File archivo;
        if("A".equals(biblioteca)) {
            return RecorreXML("biblioteca A.xml","libro",titulo); }
        else if("B".equals(biblioteca)) {
            return RecorreXML("biblioteca B.xml","titulo",titulo);
        } else {
            return RecorreXML("biblioteca C.xml","vol",titulo);
        }
    }
    
    public static String RecorreXMLporAutor (String autor, String biblioteca) throws ParserConfigurationException, SAXException, IOException{  
        File archivo;
        if("A".equals(biblioteca)) {
            return RecorreXML("biblioteca A.xml","autor",autor); }
        else if("B".equals(biblioteca)) {
            return RecorreXML("biblioteca B.xml","autor",autor);
        } else {
            return RecorreXML("biblioteca C.xml","autor",autor);
        }
    }    
}
