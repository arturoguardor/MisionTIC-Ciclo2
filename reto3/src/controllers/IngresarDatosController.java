package controllers;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Paciente;


public class IngresarDatosController {


    @FXML
    private TextArea viewDatos;
    @FXML
    private TextArea viewSalidas;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtEPS;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtEnfermedad;

    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnProcesar;

    @FXML
    void ingresarDatos(ActionEvent event) {

        String[] datos = {txtNombre.getText(), txtCedula.getText(), txtEdad.getText(), txtCiudad.getText(), txtEPS.getText(), txtEnfermedad.getText(), txtEnfermedad.getText()};

        if (prueba(datos)) viewDatos.setText(viewDatos.getText() + String.join(" ", datos) + "\n");
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);

            alerta.setTitle("Acción Denegada");
            alerta.setHeaderText("Faltan Datos");
            alerta.setContentText("Uno o más campos estan sin completar");
            alerta.showAndWait();
        }
        viewSalidas.setText("");


    }

    @FXML
    void procesarDatos(ActionEvent event) {

        String entrada = viewDatos.getText();

        if (entrada.isBlank()){
            Alert alerta = new Alert(Alert.AlertType.WARNING);

            alerta.setTitle("ADERTENCIA");
            alerta.setHeaderText("No hay datos para procesar");
            alerta.setContentText("Primero debe registrar los pacientes");
            alerta.showAndWait();
        }else{
            String[] entradas = entrada.split("\n");

            int nPacientes = entradas.length;
            
            Paciente[] pacientes = new Paciente[nPacientes];

            for (int i = 0; i < nPacientes; i++){
                
                String[] datos = entradas[i].split(" ");

                Paciente paciente = new Paciente();

                paciente.setNombre(datos[0] + " " + datos[1]);
                paciente.setCedula(datos[2]);
                paciente.setEdad(Integer.parseInt(datos[3]));
                paciente.setCiudad(datos[4]);
                paciente.setEps(datos[5]);                
                paciente.setEnfermedad(datos[6]);

                pacientes[i] = paciente;
            }

            String resultado = Paciente.getCiudad(pacientes);

            for (int i = 0; i < nPacientes; i++){

                resultado += "\n" + pacientes[i].getCedula() + " " + pacientes[i].clasificarEdad();
            }
            viewSalidas.setText(resultado);
        }
    }

    private boolean prueba(String[] datos){

        for (String dato : datos){
            
            if (dato.isBlank()){
                return false;
            }
        }
        return true;
    }
}
