package aplicacion.menu;
import aplicacion.datos.EnemigosDao;
import java.util.*;
import javax.swing.JOptionPane;
import java.util.stream.Collectors;

/**
 * Aqui vamos a desarrollar los filtros correspondientes para
 * facilitar la busqueda de datos del usuario
 */

public class ModelosImplementacion
{
    private int opcion;

    //Creamos el metodo main para hacer pruebas unitarias
    public static void main(String[] args)
    {
        ModelosImplementacion prueba = new ModelosImplementacion();
        prueba.busquedaLetra();
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
                    case 1 -> System.out.println("Hola");
                    case 2 -> System.out.println("adios");
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

    //*Creamos el primer filtro para nuestro sistema de datos
    //*FilterStats el cual va a seleccionar solo, el ataque, vida y nivel
    public void filtroStats()
    {

    }

}
