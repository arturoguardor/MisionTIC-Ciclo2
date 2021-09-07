package models;

import static java.lang.String.join;
import java.util.ArrayList;

public abstract class Persona {
    
    protected String nombre, cedula, edad, ciudad;

    public String getNombre() {
        return nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public String getEdad() {
        return edad;
    }
    public String getCiudad() {
        return ciudad;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }  
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }   

    public ArrayList<String> getPersona(){
        ArrayList<String> p = new ArrayList<String>();

        p.add(this.nombre);
        p.add(this.cedula);
        p.add(this.edad);
        p.add(this.ciudad);

        return p;
    }


    @Override
    public String toString() {
        return join(" ", nombre, cedula, edad, ciudad);
    }
    
    public abstract String getDatos();
        
}
