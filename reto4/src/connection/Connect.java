package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public Connection connect(){

        String url = "jdbc:sqlite:F:/Programacion/Mision TIC 2022/Ciclo2/Java/reto4/GuardoArturo#45/src/sql/basedatosMasSalud.db";

        Connection connectivity = null;

        try {

            connectivity = DriverManager.getConnection(url);
            System.out.println("Conexión establecida.");

        } catch (SQLException error) {

            System.out.println(error.getMessage());
            System.out.println("No se pudo conectar.");

        }
        return connectivity;
    }
}
