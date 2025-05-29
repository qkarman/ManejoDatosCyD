package aplicacion.menu;
import aplicacion.datos.EnemigosDao;
import java.util.*;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 * Aqui vamos a desarrollar los filtros correspondientes para
 * facilitar la busqueda de datos del usuario
 */

public class ModelosImplementacion
{
    private int opcion;

    private static final Logger log = LoggerFactory.getLogger(ModelosImplementacion.class);
    //Creamos el metodo main para hacer pruebas unitarias
    public static void main(String[] args)
    {
        ModelosImplementacion prueba = new ModelosImplementacion();
        prueba.busquedaLetra();
        prueba.busquedaIdEnemigo();
    }
    //Creamos un menu para gestionar los filtros que se van a usar
    public void menu()
    {
        while(opcion != 8)
        {
            try
            {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        *** BIENVENIDO AL MENU DE FILTROS ***
                        1.- Busqueda por inicial de letra
                        2.- Busqueda por id
                        3.- Busqueda por nivel de menor a menor
                        4.- Busqueda por nivel de mayor a menor
                        5.- Busqueda por tipo
                        6.- Busqueda de ataque, vida y nivel
                        7.- Busqueda por nombre, debilidad y tipo
                        8.- Salir
                        Selecciona una opcion:"""));
                switch (opcion)
                {
                    case 1 -> busquedaLetra();
                    case 2 -> busquedaIdEnemigo();
                    case 3 -> System.out.println("hol");
                    default -> System.out.println("Seleccionaste una opcion incorrecta ");
                }
            }
            catch(Exception e)
            {
                System.out.println("Tienes un error en: " + e.getMessage());
            }
        }
    }

    //*Creamos el primer filtro busqueda de letra
    public void busquedaLetra()
    {
        String letra = JOptionPane.showInputDialog(null,"Selecciona la letra de busqueda");

        //Creamos un filtro de condicion sobre el null
        if(letra == null || letra.isEmpty())
        {
            System.out.println("Entrada invalida");
            return;
        }

        EnemigosDao invocar = new EnemigosDao();

        //Creamos el algoritmo mas robusto y con condicion
        List<AtributosEnemigos>  resultado = invocar.listarEnemigos().stream()
                .filter(n -> n.getNombre().toLowerCase().startsWith(letra.toLowerCase()))
                .collect(Collectors.toList());

        if(resultado.isEmpty())
        {
            System.out.println("No se encontraron enemigos con esa letra");
        }
        else
        {
            resultado.forEach(e -> System.out.println(e.getNombre()));
        }
    }

    //*Creamos un metodo para hacer busqueda con el id del enemigo
    public void busquedaIdEnemigo()
    {
        int busqueda;

        try
        {
            busqueda = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el id del enemigo: "));
        }
        catch (NumberFormatException e)
        {
            System.out.println("Entrada invalida debe de ser un numero entero : " + e.getMessage());
            return;
        }

        if(busqueda <= 0)
        {
            JOptionPane.showMessageDialog(null,"Entrada invalida debe ser mayor a cero 0: ");
            return;
        }

        AtributosEnemigos enemigo = new AtributosEnemigos();
        enemigo.setId(busqueda);

        EnemigosDao invocar = new EnemigosDao();
        boolean encontrado = invocar.buscarEnemigoPorId(enemigo);

        if(encontrado)
        {
            System.out.println("Enemigo encontrado: " + "\n" + enemigo.toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null,"No se encontro ningun enemigo con ese ID.");
        }
    }

    //*Creamos el primer filtro para nuestro sistema de datos
    //*FilterStats el cual va a seleccionar solo, el ataque, vida y nivel
    public void filtroStats()
    {

    }

}
