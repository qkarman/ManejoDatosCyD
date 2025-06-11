package aplicacion.menu;
import aplicacion.datos.EnemigosDao;
import java.util.*;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Aquí vamos a desarrollar los filtros correspondientes para
 * facilitar la busqueda de datos del usuario
 */

public class ModelosImplementacion
{
    private static final Logger log = Logger.getLogger(ModelosImplementacion.class.getName());

    //Creamos el metodo main para hacer pruebas unitarias
    public static void main(String[] args)
    {
        ModelosImplementacion prueba = new ModelosImplementacion();
        prueba.menuFiltros();
    }
    //Creamos un menu para gestionar los filtros que se van a usar
    public void menuFiltros()
    {
        int opcion = 0;

        while(opcion != 6)
        {
            try
            {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        *** BIENVENIDO AL MENU DE FILTROS ***
                        1.- Busqueda por inicial de letra
                        2.- Busqueda por id
                        3.- Busqueda por nivel de menor a mayor
                        4.- Busqueda por tipo
                        5.- Busqueda de Nombre, ataque, vida y nivel
                        6.- Salir
                        Selecciona una opcion:"""));
                switch (opcion)
                {
                    case 1 -> filtroBusquedaLetra();
                    case 2 -> filtroBusquedaIdEnemigo();
                    case 3 -> filtroMenorMayor();
                    case 4 -> filtroBuscarTipo();
                    case 5 -> filtroStats();
                    case 6 -> log.info("Saliendo del programa jefe....");
                    default -> log.info("Seleccionaste una opcion incorrecta ");
                }
            }
            catch(Exception e)
            {
                log.info("Tienes un error en: " + e.getMessage());
            }
        }
    }

    //*Creamos el primer filtro busqueda de letra
    public void filtroBusquedaLetra()
    {
        String letra = JOptionPane.showInputDialog(null,"Selecciona la letra de busqueda");

        //Creamos un filtro de condicion sobre el null
        if(letra == null || letra.isEmpty())
        {
            log.info("Entrada invalida");
            return;
        }

        EnemigosDao invocar = new EnemigosDao();

        //Creamos el algoritmo mas robusto y con condicion
        List<AtributosEnemigos>  resultado = invocar.listarEnemigos().stream()
                .filter(n -> n.getNombre().toLowerCase().startsWith(letra.toLowerCase()))
                .collect(Collectors.toList());

        if(resultado.isEmpty())
        {
            log.info("No se encontraron enemigos con esa letra");
        }
        else
        {
            resultado.forEach(e -> log.info(e.getNombre()));
        }
    }

    //*Creamos un metodo para hacer busqueda con el id del enemigo
    public void filtroBusquedaIdEnemigo()
    {
        int busqueda;

        try
        {
            busqueda = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el id del enemigo: "));
        }
        catch (NumberFormatException e)
        {
           log.info("Entrada invalida debe de ser un numero entero : " + e.getMessage());
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
            log.info("Enemigo encontrado: " + "\n" + enemigo.toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null,"No se encontro ningún enemigo con ese ID.");
        }
    }

    //*Creamos el metodo para buscar por nivel de menor a mayor
    public void filtroMenorMayor()
    {
        int opciones = 0;

        try
        {
            opciones = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Selecciona 1 para ordenar enemigos por NIVEL de menor a mayor. \n" +
                    "Selecciona 2 para ordenar enemigos por nivel de mayor a menor"));
        }
        catch(NumberFormatException e)
        {
            log.info("Entrada invalida: " + e.getMessage());
            return;
        }

        EnemigosDao nivelEnemigo = new EnemigosDao();
        List<AtributosEnemigos> lista = nivelEnemigo.listarEnemigos();

        if(opciones == 1)
        {
            //Ordenar de menor a mayor por nivel
            lista.stream().sorted(Comparator.comparingInt(AtributosEnemigos::getNivel))
                    .forEach(System.out::println);
        }
        else if(opciones == 2)
        {
            //Ordenar de mayor a menor
            lista.stream().sorted(Comparator.comparingInt(AtributosEnemigos::getNivel).reversed())
                            .forEach(System.out::println);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Regresando a menu de filtros...");
        }
    }

    //*Creamos un filtro para buscar tipo
    public void filtroBuscarTipo()
    {
        int opcions = 0;

        try
        {
            opcions = Integer.parseInt(JOptionPane.showInputDialog(null,"Si quieres usar el" +
                    "filtro de tipo de enemigos selecciona 1."));
        }
        catch (NumberFormatException e)
        {
            log.info("Entrada invalida! " + e.getMessage());
        }

        EnemigosDao tipoEnemigo = new EnemigosDao();
        List<AtributosEnemigos> listaTipo = tipoEnemigo.listarEnemigos();

        if(opcions == 1)
        {
            Set<String> tiposUnicos = listaTipo.stream()
                    .map(AtributosEnemigos::getTipo)
                    .filter(tipo -> tipo != null && !tipo.trim().isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            tiposUnicos.forEach(e -> log.info(e.toString()));
        }
        else
        {
            log.info("Saliendo al menu de filtros jefe....");
        }
    }
    //*Creamos el primer filtro para nuestro sistema de datos
    //*FilterStats el cual va a seleccionar solo, el ataque, vida y nivel
    public void filtroStats()
    {
        String eleccion;
        try
        {
            eleccion = JOptionPane.showInputDialog(null,"Seguro quieres filtrar los stats [y]/[n]");
        }
        catch(Exception e)
        {
            log.info("Seleccionaste una opcion incorrecta: " + e.getMessage());
            return;
        }

        if(eleccion == null || eleccion.isEmpty()) return;

        if(eleccion.equalsIgnoreCase("y"))
        {
            EnemigosDao stats = new EnemigosDao();
            List<AtributosEnemigos> listaStats = stats.listarEnemigos();

            listaStats.forEach(e -> JOptionPane.showMessageDialog(null,
                    "****STATS ENEMIGOS****" + "\n" +
                    "Nombre: " + e.getNombre() + "\n" +
                    " Ataque: " + e.getAtaque() + "\n" +
                    " Vida: " + e.getVida() + "\n" +
                    " Nivel: " + e.getNivel()
            ));
        }
        else
        {
            log.info("Saliendo al menu de filtros jefe....");
        }
    }
}
