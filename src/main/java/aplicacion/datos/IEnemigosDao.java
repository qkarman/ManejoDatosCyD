package aplicacion.datos;
import aplicacion.menu.AtributosEnemigos;
import java.util.List;

/**
 * Interfaz para definir las operaciones CRUD para manejar la logica de datos
 */

public interface IEnemigosDao
{
    //*Metodo para listar todos los enemigos
    List<AtributosEnemigos> listarEnemigos();

    //*Metodo para buscar un enemigo por ID
    boolean buscarEnemigoPorId(AtributosEnemigos enemigos);

    //*Metodo para agregar nuevos enemigos
    boolean agregarNuevosEnemigos(AtributosEnemigos enemigos);

    //*Metodo para modificar un enemigo
    boolean modificarEnemigo(AtributosEnemigos enemigos);

    //*Metodo para eliminar un enemigo
    boolean eliminarEnemigo(AtributosEnemigos enemigos);
}
