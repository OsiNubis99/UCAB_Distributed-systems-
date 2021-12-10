/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

/**
 *
 * @author Raikjars
 */
public class LibroModel {
    
    private int idLibro;
    private String titulo;
    private String autor;
    
    public LibroModel(int idLibro, String titulo, String autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
