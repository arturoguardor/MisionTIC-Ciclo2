package models;

public abstract class Persona {
    
    protected int edad;
    protected String nombre, cedula, ciudad, eps, diagnostico;

    public int getEdad() {
        return edad;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public String getCiudad() {
        return ciudad;
    }
    public String getEps() {
        return eps;
    }
    public String getDiagnostico() {
        return diagnostico;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }  
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }   
    public void setEps(String eps) {
        this.eps = eps;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    @Override
    public String toString() {
        return String.join(" ", nombre, cedula, String.valueOf(edad), ciudad);
    }
    
    public abstract String getDatos();
        
}
