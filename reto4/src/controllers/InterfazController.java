package controllers;

import models.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;

import connection.Connect;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class InterfazController{
    void clearTxt() {
        txtIngresarNombre.setText("");
        txtIngresarCedula.setText("");
        txtIngresarEdad.setText("");
        txtIngresarCiudad.setText("");
        txtIngresarEPS.setText("");
        txtIngresarEnfermedad.setText("");
    }

    void editTxtFalse(){
        txtEditarNombre.setEditable(false);
        txtEditarCedula.setEditable(false);
        txtEditarEdad.setEditable(false);
        txtEditarCiudad.setEditable(false);
        txtEditarEPS.setEditable(false);
        txtEditarEnfermedad.setEditable(false);
    }
    void editTxtTrue(){
        txtEditarNombre.setEditable(true);
        txtEditarCedula.setEditable(true);
        txtEditarEdad.setEditable(true);
        txtEditarCiudad.setEditable(true);
        txtEditarEPS.setEditable(true);
        txtEditarEnfermedad.setEditable(true);
    }
    void clearEditTxt() {
        txtBuscarCedula.setText("");
        txtEditarNombre.setText("");
        txtEditarCedula.setText("");
        txtEditarEdad.setText("");
        txtEditarCiudad.setText("");
        txtEditarEPS.setText("");
        txtEditarEnfermedad.setText("");
    }

    @FXML
    private TextField txtIngresarNombre;
    @FXML
    private TextField txtIngresarCedula;
    @FXML
    private TextField txtIngresarEdad;
    @FXML
    private TextField txtIngresarCiudad;
    @FXML
    private TextField txtIngresarEPS;
    @FXML
    private TextField txtIngresarEnfermedad;
    @FXML
    private Button btnIngresar;
    @FXML
    void ingresarPaciente(ActionEvent event) {

        if (txtIngresarNombre.getText().isBlank() || txtIngresarCedula.getText().isBlank() || txtIngresarEdad.getText().isBlank() || txtIngresarCiudad.getText().isBlank() || txtIngresarEPS.getText().isBlank() || txtIngresarEnfermedad.getText().isBlank()) {

            System.out.println("Error: Una o varias asillas vacias");

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("ADVERTENCIA");
            alerta.setHeaderText("Existe una o varias casillas vacias");
            alerta.setContentText("No es posible procesar los datos del paciente, favor ingresarlos en todas las casillas.");
            alerta.showAndWait();

        }else{
            Connect objConnect = new Connect();
            String name = txtIngresarNombre.getText();
            Long id = Long.parseLong(txtIngresarCedula.getText());
            Integer age = Integer.parseInt(txtIngresarEdad.getText());
            String city = txtIngresarCiudad.getText();
            String eps = txtIngresarEPS.getText();
            String sick = txtIngresarEnfermedad.getText();

            String sql = "INSERT INTO Pacientes(nombre,cedula,edad,ciudad,eps,enfermedad) VALUES(?,?,?,?,?,?)";

            try {
                Connection connectivity = objConnect.connect();
                PreparedStatement ps = connectivity.prepareStatement(sql);
                ps.setString(1, name);
                ps.setLong(2, id);
                ps.setInt(3, age);
                ps.setString(4, city);
                ps.setString(5, eps);
                ps.setString(6, sick);
                ps.executeUpdate();

                System.out.println("Paciente Creado");

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("INGRESO DE DATOS");
                alerta.setHeaderText("Ingreso de datos exitoso");
                alerta.setContentText("Paciente ingresado correctamente en el sistema.");
                alerta.showAndWait();
                
                clearTxt();
                connectivity.close();

            } catch (SQLException error) {
                System.out.println(error.getMessage());
                System.out.println("Error: ingreso del paciente fallido");
           }
        }
    
    }


    @FXML
    private TextArea viewBaseDatos;
    @FXML
    private TextArea viewDatosProcesados;
    @FXML
    private Button btnObtenerDatos;
    @FXML
    private Button btnProcesarDatos;
    @FXML
    void obtenerDatos(ActionEvent event) {
        
        Connect objConnect = new Connect();
        String query = "SELECT * FROM Pacientes";

        try {
            Connection connectivity = objConnect.connect();
            Statement stmt = connectivity.createStatement();
            ResultSet result = stmt.executeQuery(query);

            ResultSetMetaData rsmd = result.getMetaData();
            int numColumn = rsmd.getColumnCount();
            String paciente = "";
            while (result.next()) {
                for (int i = 1; i <= numColumn; i++) {
                    String valueColumn = result.getString(i);
                    if(i != numColumn){
                        paciente += valueColumn + " ";
                    }else{
                        paciente += valueColumn + "\n";
                    }
                    
                }
                viewBaseDatos.setText(paciente);
            }
            System.out.println("Consuta Exitosa");
            connectivity.close();
        
        } catch (SQLException error) {

            System.out.println(error.getMessage());
            System.out.println("Error: consulta fallida");

        }

    }
    @FXML
    void procesarDatos(ActionEvent event) {

        String entrada = viewBaseDatos.getText();

        if (entrada.isBlank()){

            System.out.println("Error: sin datos para procesar");

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ADVERTENCIA");
            alerta.setHeaderText("No hay datos para procesar");
            alerta.setContentText("Primero debe consultar o registrar los datos de los pacientes.");
            alerta.showAndWait();

        }else{
            String[] entradas = entrada.split("\n");

            int nPacientes = entradas.length;
            
            Paciente[] pacientes = new Paciente[nPacientes];

            for (int i = 0; i < nPacientes; i++){
                
                String[] datos = entradas[i].split(" ");

                Paciente paciente = new Paciente();

                paciente.setNombre(datos[0] + " " + datos[1]);
                paciente.setCedula(Long.parseLong(datos[2]));
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
            viewDatosProcesados.setText(resultado);
        }
        
    }


    @FXML
    private TextField txtBuscarCedula;
    @FXML
    private TextField txtEditarNombre;
    @FXML
    private TextField txtEditarCedula;
    @FXML
    private TextField txtEditarEdad;
    @FXML
    private TextField txtEditarCiudad;
    @FXML
    private TextField txtEditarEPS;
    @FXML
    private TextField txtEditarEnfermedad;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    void BuscarCedulaPaciente(ActionEvent event) {
       
        if (txtBuscarCedula.getText().isBlank()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);

            alerta.setTitle("ADVERTENCIA");
            alerta.setHeaderText("No hay datos para procesar");
            alerta.setContentText("Primero debe ingresar la cedula del paciente.");
            alerta.showAndWait();

        }else{

            Connect objConnect = new Connect();
            String query = "SELECT * FROM Pacientes WHERE cedula=?";
            Long id = Long.parseLong(txtBuscarCedula.getText());

            try {
                Connection connectivity = objConnect.connect();
                PreparedStatement ps = connectivity.prepareStatement(query);
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                txtEditarNombre.setText(rs.getString("nombre"));
                txtEditarCedula.setText(String.valueOf(rs.getLong("cedula")));
                txtEditarEdad.setText(String.valueOf(rs.getInt("edad")));
                txtEditarCiudad.setText(rs.getString("ciudad"));
                txtEditarEPS.setText(rs.getString("eps"));
                txtEditarEnfermedad.setText(rs.getString("enfermedad"));

                editTxtTrue();
                System.out.println("Paciente Encontrado");
                connectivity.close();
                txtBuscarCedula.setEditable(false);
                
            }catch (SQLException error) {

                System.out.println(error.getMessage());
                System.out.println("Paciente no Existe");

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ADVERTENCIA");
                alerta.setHeaderText("No es posible procesar los datos");
                alerta.setContentText("La cedula ingresada no pertenece a un paciente existente.");
                alerta.showAndWait();
            }
        }
    }
    @FXML
    void editarPaciente(ActionEvent event) {

        if (txtBuscarCedula.getText().isBlank() || txtEditarNombre.getText().isBlank() || txtEditarCedula.getText().isBlank() || txtEditarEdad.getText().isBlank() || txtEditarCiudad.getText().isBlank() || txtEditarEPS.getText().isBlank() || txtEditarEnfermedad.getText().isBlank()) {

            System.out.println("Error: Una o varias asillas vacias");

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("ADVERTENCIA");
            alerta.setHeaderText("Existe una o varias casillas vacias");
            alerta.setContentText("No es posible procesar los datos del paciente, favor ingresarlos en todas las casillas.");
            alerta.showAndWait();

        }else{
            Connect objConnect = new Connect();
            
            Long id_find = Long.parseLong(txtBuscarCedula.getText());

            String name = txtEditarNombre.getText();
            Long id = Long.parseLong(txtEditarCedula.getText());
            Integer age = Integer.parseInt(txtEditarEdad.getText());
            String city = txtEditarCiudad.getText();
            String eps = txtEditarEPS.getText();
            String sick = txtEditarEnfermedad.getText();


            String query = "UPDATE Pacientes SET nombre=? , cedula=? , edad=?, ciudad=? , eps=? , enfermedad=? WHERE cedula=?";

            try {
                Connection connectivity = objConnect.connect();
                PreparedStatement ps = connectivity.prepareStatement(query);
                ps.setString(1, name);
                ps.setLong(2, id);
                ps.setInt(3, age);
                ps.setString(4, city);
                ps.setString(5, eps);
                ps.setString(6, sick);
                ps.setLong(7, id_find);
                ps.executeUpdate();
                
                System.out.println("Datos del Paciente Actualizados");
                
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("ACTUALIZACIÓN DE DATOS");
                alerta.setHeaderText("Actualización Exitosa");
                alerta.setContentText("Datos del paciente actualizados correctamente.");
                alerta.showAndWait();
                
                clearEditTxt();
                txtBuscarCedula.setEditable(true);
                editTxtFalse();
                connectivity.close();

            } catch (SQLException error) {
                System.out.println(error.getMessage());
                System.out.println("Error: Conexion Fallida");

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Conexion Fallida");
                alerta.setContentText("Problemas en la conexion a la base de datos.");
                alerta.showAndWait();

            }
         }
     }
    
    @FXML
    void eliminarPaciente(ActionEvent event) {
        if (txtBuscarCedula.getText().isBlank() || txtEditarNombre.getText().isBlank() || txtEditarCedula.getText().isBlank() || txtEditarEdad.getText().isBlank() || txtEditarCiudad.getText().isBlank() || txtEditarEPS.getText().isBlank() || txtEditarEnfermedad.getText().isBlank()) {

            System.out.println("Error: Una o varias asillas vacias");

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("ADVERTENCIA");
            alerta.setHeaderText("Existe una o varias casillas vacias");
            alerta.setContentText("No es posible procesar los datos del paciente, favor ingresarlos en todas las casillas.");
            alerta.showAndWait();

        }else{
            Connect objConnect = new Connect();
            
            Long id = Long.parseLong(txtEditarCedula.getText());

            String query = "DELETE FROM Pacientes WHERE cedula=?";

            try {
                Connection connectivity = objConnect.connect();
                PreparedStatement ps = connectivity.prepareStatement(query);
            
                ps.setLong(1, id);
                ps.executeUpdate();
                
                System.out.println("Datos del paciente eliminados");
                
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("ACTUALIZACIÓN DE DATOS");
                alerta.setHeaderText("Datos Eliminados Exitosamente");
                alerta.setContentText("Datos del paciente eliminados correctamente.");
                alerta.showAndWait();
                
                clearEditTxt();
                txtBuscarCedula.setEditable(true);
                editTxtFalse();
                connectivity.close();

            } catch (SQLException error) {
                System.out.println(error.getMessage());
                System.out.println("Error: Conexion Fallida");

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Conexion Fallida");
                alerta.setContentText("Problemas en la conexion a la base de datos.");
                alerta.showAndWait();

            }
        }
    }
}
