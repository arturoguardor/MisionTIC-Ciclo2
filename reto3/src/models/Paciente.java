package models;

import java.lang.reflect.Array;

public class Paciente extends Persona {

    private String enfermedad, eps;

    public String getEnfermedad() {
        return enfermedad;
    }
    public String getEps() {
        return eps;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
    public void setEps(String eps) {
        this.eps = eps;
    }


    public String clasificarEdad(){
        int edad = getEdad();
        if (edad >= 21 && edad <= 30) {
            return "Joven adulto";}
        else if (edad > 30 && edad <= 60) {
            return "Adulto";}
        else if (edad > 60) {
            return "Tercera edad";}
        return "Niño o Adolecente";
    }

    
    public static String getCiudad(Paciente[] pacientes){
        return Array.mode(pacientes);
    }
    

    @Override
    public String toString() {
        return eps + " " + enfermedad;
    }
    @Override
    public String getDatos() {
        return String.join(" ", super.toString(), this.toString());
    }
}
