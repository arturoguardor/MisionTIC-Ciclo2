package models;

import java.util.ArrayList;

public class Paciente extends Persona {

    private String eps, enfermedad;
    public ArrayList<ArrayList<String>> baseDatosPacientes = new ArrayList();
    
    public void addBaseDatosPacientes(){
        ArrayList<String> datosPaciente = new ArrayList<String>();
        datosPaciente.addAll(super.getPersona());
        datosPaciente.addAll(this.getPaciente());
        baseDatosPacientes.add(datosPaciente);
        baseDatosPacientes.add(new ArrayList<String>());
        System.out.println(baseDatosPacientes);
    }

    public ArrayList<String> getPaciente(){
        ArrayList<String> p = new ArrayList<String>();

        p.add(this.eps);
        p.add(this.enfermedad);

        return p;
    }

    public String getBaseDatosPacientes(){
        String datosPacientes = "";

        for(int i = 0 ; i < baseDatosPacientes.size() ; i++){
            
            for(int j = 0 ; j < baseDatosPacientes.get(i).size() ; j++){
                datosPacientes += baseDatosPacientes.get(i).get(j) + "\n"; 
            }
        }

        return datosPacientes;
    }

    public String getEps() {
        return eps;
    }
    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String clasificarEdad(){
        int edad = Integer.parseInt(getEdad());
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
        return " " + getEps() + " " + getEnfermedad();
    }
    @Override
    public String getDatos() {
        return String.join(" ", super.toString(), this.toString());
    }
}
