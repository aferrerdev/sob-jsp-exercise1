
import javax.servlet.http.HttpServletRequest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class Persona {
    public String dni;
    public String nom;
    public String cognom1;
    public String cognom2;

    public Persona(String dni, String nom, String cognom1, String cognom2) {
        this.dni = dni;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
    }
    
    public Persona(HttpServletRequest request){
        this.dni = request.getParameter("dni");
        this.nom = request.getParameter("nom");
        this.cognom1 = request.getParameter("cognom1");
        this.cognom2 = request.getParameter("cognom2");
    }
    
    public boolean checkDni(){
        return !dni.equals("");
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }
    
    
}
