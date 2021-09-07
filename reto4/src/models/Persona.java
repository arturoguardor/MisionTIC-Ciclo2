package models;

public abstract class Persona {
    
    private Long cedula;
    private Integer edad;
    private String nombre, ciudad, eps, enfermedad;

    public int getEdad() {
        return edad;
    }
    public String getNombre() {
        return nombre;
    }
    public Long getCedula() {
        return cedula;
    }
    public String getCiudad() {
        return ciudad;
    }
    public String getEps() {
        return eps;
    }
    public String getEnfermedad() {
        return enfermedad;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }  
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }   
    public void setEps(String eps) {
        this.eps = eps;
    }
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public String toString() {
        return String.join(" ", nombre, String.valueOf(cedula), String.valueOf(edad), ciudad);
    }
    
    public abstract String getDatos();
        
}
