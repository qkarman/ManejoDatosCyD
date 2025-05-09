package aplicacion.datos;
import aplicacion.conexion.Conexion;
import aplicacion.menu.AtributosEnemigos;


/**
 * Siguiente paso en clase menu invocar todos los metodos
 */

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static aplicacion.conexion.Conexion.getConexion;

public class EnemigosDao implements IEnemigosDao
{
    private static final Logger logger = Logger.getLogger(EnemigosDao.class.getName());

    //!Creamos el metodo main para hacer pruebas unitarias
    public static void main(String[] args)
    {
        IEnemigosDao enemigosDao = new EnemigosDao();

        //Agregar enemigos
        var nuevoEnemigo = new AtributosEnemigos(10,2,5,"Orco","Cazadores","Humanoide");
        var agregado = enemigosDao.agregarNuevosEnemigos(nuevoEnemigo);

        if(agregado)
        {
            logger.log(Level.SEVERE,"Enemigo agregado {0}", nuevoEnemigo);
        }
        else
        {
            logger.severe("No se agrego el enemigo" + nuevoEnemigo.getId());
        }

        //Listar clientes
        logger.info("Listar enemigos");

        var enemigo = enemigosDao.listarEnemigos();
        enemigo.forEach(System.out::println);
    }

    //*Metodo para listar enemigos, y almacenar la informacion en una base de datos
    @Override
    public List<AtributosEnemigos> listarEnemigos()
    {
        List<AtributosEnemigos> enemigos = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM enemigos ORDER BY id";

        try
        {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next())
            {
                var enemigo = new AtributosEnemigos();
                enemigo.setId(rs.getInt("id"));
                enemigo.setNivel(rs.getInt("nivel"));
                enemigo.setAtaque(rs.getInt("ataque"));
                enemigo.setVida(rs.getInt("vida"));
                enemigo.setNombre(rs.getString("nombre"));
                enemigo.setDebilidad(rs.getString("debilidad"));
                enemigo.setTipo(rs.getString("tipo"));
                enemigos.add(enemigo);
            }
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error al listar enemigos {0}", e.getMessage());
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE,"Error al cerrar la conexion {0}", e.getMessage());
            }
        }
        return enemigos;
    }

    //*Metodo para buscar enemigos con el id
    @Override
    public boolean buscarEnemigoPorId(AtributosEnemigos enemigos)
    {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM enemigo WHERE id = ?";

        try
        {
            ps = con.prepareStatement(sql);
            ps.setInt(1, enemigos.getId());
            rs = ps.executeQuery();

            if(rs.next())
            {
                enemigos.setNivel(rs.getInt("nivel"));
                enemigos.setAtaque(rs.getInt("ataque"));
                enemigos.setVida(rs.getInt("vida"));
                enemigos.setNombre(rs.getString("nombre"));
                enemigos.setDebilidad(rs.getString("debilidad"));
                enemigos.setTipo(rs.getString("tipo"));

                return true;
            }
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE,"Error al recuperar enemigo por id {0}", e.getMessage());
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (Exception e )
            {
                logger.log(Level.SEVERE,"Error al cerrar la conexion {0}", e.getMessage());
            }
        }
        return false;
    }

    //*Metodo para agregar nuevos enemigos a lista de la base de datos
    @Override
    public boolean agregarNuevosEnemigos(AtributosEnemigos enemigos)
    {
        PreparedStatement ps;
        Connection con =  getConexion();
        String sql = "INSERT INTO enemigos(nivel, ataque, vida, nombre, debilidad, tipo)" + "VALUES (?, ?, ?, ?, ?, ?)";

        try
        {
            ps = con.prepareStatement(sql);
            ps.setInt(1,enemigos.getNivel());
            ps.setInt(2,enemigos.getAtaque());
            ps.setInt(3,enemigos.getVida());
            ps.setString(4,enemigos.getNombre());
            ps.setString(5,enemigos.getDebilidad());
            ps.setString(6,enemigos.getTipo());
            ps.execute();

            return true;
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE,"Error al agregar un enemigo" + e.getMessage());
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE,"Error al cerrar la conexion {0}", e.getMessage());
            }
        }
        return false;
    }

    //*Metodo para modificar el enemigo
    @Override
    public boolean modificarEnemigo(AtributosEnemigos enemigos)
    {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE enemigos SET nivel = ?, ataque = ?, vida = ?, nombre = ?, debilidad = ?, tipo = ?, WHERE id = ?";

        try
        {
            ps = con.prepareStatement(sql);
            ps.setInt(1,enemigos.getNivel());
            ps.setInt(2,enemigos.getAtaque());
            ps.setInt(3,enemigos.getVida());
            ps.setString(4,enemigos.getNombre());
            ps.setString(5,enemigos.getDebilidad());
            ps.setString(6,enemigos.getTipo());
            ps.execute();
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE,"Error al modificar el enemigo" + e.getMessage());
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE,"Error al cerrar la conexion de la base de datos {0}" + e.getMessage());
            }
        }
        return false;
    }

    //*Metodo para eliminar enemigos
    @Override
    public boolean eliminarEnemigo(AtributosEnemigos enemigos)
    {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM enemigos WHERE id = ?";

        try
        {
            ps = con.prepareStatement(sql);
            ps.setInt(1,enemigos.getId());
            ps.execute();
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE,"Error al eliminar un enemigo {0}", e.getMessage());
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE,"Error al cerrar la conexion {0}", e.getMessage());
            }
        }
        return false;
    }
}
