package models;

import java.util.ArrayList;

public class Medico extends Persona{

    String especialidad, codigo;

    private ArrayList<ArrayList<String>> addBaseDatosMedicos = new ArrayList();
    public void addBaseDatosMedicos(){
        ArrayList<String> datosMedico = new ArrayList<String>();
        datosMedico.addAll(super.getPersona());
        datosMedico.addAll(this.getMedico());
        addBaseDatosMedicos.add(datosMedico);
        addBaseDatosMedicos.add(new ArrayList<String>());
        System.out.println(addBaseDatosMedicos);
    }

    public ArrayList<String> getMedico(){
        ArrayList<String> p = new ArrayList<String>();

        p.add(this.especialidad);
        p.add(this.codigo);

        return p;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public static String paciente(Paciente enfermedad){
        // String listadoEnfermedades[] = new String [];
        enfermedad.getEnfermedad();

        return "Niño o Adolecente";
    }


    @Override
    public String getDatos() {
        return String.join(" ", super.toString(), this.toString());
    }
    
    
}
