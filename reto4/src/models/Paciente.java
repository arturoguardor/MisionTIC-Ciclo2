package models;

public class Paciente extends Persona {


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
        return MaxCiudad.mode(pacientes);
    }

    @Override
    public String toString() {
        return getNombre() + " " + getCedula() + " " + getEdad() + " " + getCiudad() + " " + getEps() + " " + getEnfermedad();
    }
    @Override
    public String getDatos() {
        return String.join(" ", super.toString(), this.toString());
    }
}
