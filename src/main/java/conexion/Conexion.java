package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion
{
    //Creamos un objeto de tipo logger
    private static final Logger logger = Logger.getLogger(Conexion.class.getName());

    //!Metodo para establecer la conexion con la base de datos
    public static Connection getConexion()
    {
        //Variable para almacenar la conexion
        Connection conexion = null;

        //Nombre de la base de datos
        var baseDatos = "calabozosydragones";

        //Url de la base de datos
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;

        //Credenciales de la base de datos
        var usuario = "root";
        var password = "Pokemones15-";

        try
        {
            //Carga el controlador JDBC para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Intentar establecer la conexion con los parametros
            conexion = DriverManager.getConnection(url,usuario,password);
        }
        catch (Exception n)
        {
            logger.warning("Error al conectarse ala BD" + n.getMessage());
        }

        //Retornamos el objeto Connection
        return conexion;
    }

    public static void main(String[] args)
    {
        //Llama al metodo para obtener la conexion
        var conexion = Conexion.getConexion();

        //Se verifica si la conexion fue exitosa
        if(conexion != null)
        {
            logger.log(Level.INFO, "Conexion exitosa: {0} ", conexion);
        }
        else
        {
            logger.severe("Error de conexion");
        }
    }
}
