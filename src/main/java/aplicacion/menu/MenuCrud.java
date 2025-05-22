package aplicacion.menu;
import aplicacion.datos.EnemigosDao;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuCrud
{
    //Creamos nuestra variable global
    protected int activador;
    private final Logger mensaje = Logger.getLogger(MenuCrud.class.getName());

    AtributosEnemigos invocar = new AtributosEnemigos();

    //Creamos la instancia de EnemigosDao
    private final EnemigosDao enemigosDao = new EnemigosDao();

    //*Creamos la funcion main para hacer pruebas
    public static void main(String[] args)
    {
        MenuCrud invocar = new MenuCrud();
        invocar.menu();
    }

    //*Creamos la funcion del menu
    public void menu()
    {
        while(activador != 6)
        {
            try
            {
                activador = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        *** Menu CRUD ***
                        1.- Crear enemigo
                        2.- Listar enemigo
                        3.- Modificar enemigo
                        4.- Eliminar enemigo
                        5.- Filtrar enemigo
                        6.- Salir
                        Selecciona una opcion:
                        """));

                switch (activador)
                {
                    case 1 -> crearEnemigo();
                    case 2 -> listarEnemigos();
                    case 3 -> modificarEnemigo();
                    case 4 -> eliminarEnemigo();
                    case 5 -> JOptionPane.showMessageDialog(null,"Hola");
                    case 6 -> JOptionPane.showMessageDialog(null,"Nos vemos pronto Capitan");
                    default -> mensaje.warning("Escribiste una opcion incorrecta");
                }
            }
            catch(Exception e)
            {
                mensaje.warning("Estas digitando mal las opciones " + e.getMessage());
            }
        }
    }

    //*Creamos el metodo para listar enemigos de la base de datos
    public void listarEnemigos()
    {
        //Llamamos al metodo listar enemigos desde EnemigosDao
        var enemigos = enemigosDao.listarEnemigos();

        //Verificamos si tenemos enemigos para mostrar
        if (enemigos.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"No hay enemigos que mostrar");
        }
        else
        {
            enemigos.forEach(enemigo -> JOptionPane.showMessageDialog(null,enemigo));
        }
    }

    //*Creamos el metodo para agregar un enemigo
    public void crearEnemigo()
    {
        //Llamamos el metodo crear enemigos desde EnemigosDAo
        try
        {
            int nivel = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el nivel del enemigo: "));
            int ataque = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el ataque del enemigo: "));
            int vida = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita la vida del enemigo: "));
            String nombre = JOptionPane.showInputDialog("Digita el nombre del enemigo: ");
            String debilidad = JOptionPane.showInputDialog("Digita la debilidad del enemigo: ");
            String tipo = JOptionPane.showInputDialog("Digita el tipo del enemigo: ");

            AtributosEnemigos nuevo = new AtributosEnemigos();
            nuevo.setNivel(nivel);
            nuevo.setAtaque(ataque);
            nuevo.setVida(vida);
            nuevo.setNombre(nombre);
            nuevo.setDebilidad(debilidad);
            nuevo.setTipo(tipo);

            boolean exito = enemigosDao.agregarNuevosEnemigos(nuevo);

            if(exito)
            {
                JOptionPane.showMessageDialog(null,"Enemigo creado con exito");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No fue posible crear el enemigo");
            }
        }
        catch (Exception e)
        {
            mensaje.warning("Error al crear el enemigo " + e.getMessage());
        }
    }

    //*Creamos el metodo para modificar enemigo
    public void modificarEnemigo()
    {
        try
        {
            int busquedaId = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el id del enemigo a modificar"));
            invocar.setId(busquedaId);

            if(!enemigosDao.buscarEnemigoPorId(invocar))
            {
                JOptionPane.showMessageDialog(null,"Enemigo no encontrado");
                return;
            }

            int opcion = 0;

            while(opcion != 7)
            {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        Que deseas modificar?
                        1.- Nivel
                        2.- Ataque
                        3.- Vida
                        4.- Nombre
                        5.- Debilidad
                        6.- Tipo
                        7.- Salir
                        Selecciona una opcion:
                        """));

                switch (opcion)
                {
                    case 1 -> nuevoNivel();

                    case 2 -> nuevoAtaque();

                    case 3 -> nuevaVida();

                    case 4 -> nuevoNombre();

                    case 5 -> nuevaDebilidad();

                    case 6 -> nuevoTipo();

                    case 7 -> mensaje.log(Level.INFO,"Nos vemos jefe");

                    default -> mensaje.warning("Escribiste una opcion incorrecta");
                }
            }

            //Guardamos cambios en la base de datos
            boolean exito = enemigosDao.modificarEnemigo(invocar);

            if(exito)
            {
                JOptionPane.showMessageDialog(null,"El enemigo se modifico correctamente");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error al modificar enemigo");
            }
        }
        catch (Exception e)
        {
            mensaje.warning("Error en: " + e.getMessage());
        }
    }

    //*Creamos el metodo para eliminar un enemigo
    public void eliminarEnemigo()
    {
        try
        {
            int busquedaId = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el id del enemigo a eliminar: "));

            invocar.setId(busquedaId);

            if(enemigosDao.eliminarEnemigo(invocar))
            {
                JOptionPane.showMessageDialog(null,"Error el enemigo no se elimino correctamente, verifique el id");
            }
            else
            {

                JOptionPane.showMessageDialog(null,"Enemigo eliminado correctamente");
            }
        }
        catch (Exception e)
        {
            mensaje.warning("Error en: " + e.getMessage());
        }
    }

    //*Metodos para modular el código de modificacion
    //*Metodo para el nuevo nivel
    public void nuevoNivel()
    {
        boolean entradaValida = false;
        while(!entradaValida)
        {
            try
            {
                int nuevoNivel = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el nuevo nivel del enemigo: "));

                invocar.setNivel(nuevoNivel);
                mensaje.log(Level.INFO,"Nivel modificado satisfactoriamente, tu nuevo nivel es: {0}", new Object[]{invocar.getNivel()});
                entradaValida = true;
            }
            catch(NumberFormatException e)
            {
                mensaje.warning("Solo se aceptan numeros enteros: " + e.getMessage());
            }
        }
    }

    //*Metodo para el nuevo ataque
    public void nuevoAtaque()
    {
        boolean entradavalida = false;
        while(!entradavalida)
        {
            try
            {
                int nuevoAtaque = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el nuevo ataque del enemigo: "));
                invocar.setAtaque(nuevoAtaque);
                mensaje.log(Level.INFO,"Ataque modificado satisfactoriamente, tu nuevo ataque es: {0}", new Object[]{invocar.getAtaque()});
                entradavalida = true;
            }
            catch(NumberFormatException e)
            {
                mensaje.warning("Solo se aceptan numeros enteros" + e.getMessage());
            }
        }
    }

    //*Metodo para la nueva vida
    public void nuevaVida()
    {
        boolean entradavalida = false;
        while(!entradavalida)
        {
            try
            {
                int nuevaVida = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita la nueva vida del enemigo: "));
                invocar.setVida(nuevaVida);
                mensaje.log(Level.INFO,"Vida modificada satisfactoriamente, tu nueva vida es: {0}", new Object[]{invocar.getVida()});
                entradavalida = true;
            }
            catch(Exception e)
            {
                mensaje.warning("Solo se aceptan numeros enteros" + e.getMessage());
            }
        }
    }

    //*Metodo para el nuevo nombre
    public void nuevoNombre()
    {
        boolean entradavalida = false;
        while(!entradavalida)
        {
            try
            {
                String nuevoNombre = JOptionPane.showInputDialog(null,"Digita el nuevo nombre: ");
                invocar.setNombre(nuevoNombre);
                mensaje.log(Level.INFO,"Nombre modificado satisfactoriamente, tu nuevo nombre es: {0}", new Object[]{invocar.getNombre()});
                entradavalida = true;
            }
            catch (Exception e)
            {
                mensaje.warning("Solo se aceptan letras No numeros " + e.getMessage());
            }
        }
    }

    //*Metodo para la nueva debilidad
    public void nuevaDebilidad()
    {
        boolean entradavalida = false;
        while(!entradavalida)
        {
            try
            {
                String nuevaDebilidad = JOptionPane.showInputDialog(null,"Digita la nueva debilidad: ");
                invocar.setDebilidad(nuevaDebilidad);
                mensaje.log(Level.INFO,"Debilidad modificada satisfactoriamente, tu nueva debilidad es: {0}", new Object[]{invocar.getDebilidad()});
                entradavalida = true;
            }
            catch(Exception e)
            {
                mensaje.warning("Solo se aceptan letras NO numeros " + e.getMessage());
            }
        }
    }

    //*Metodo para el nuevo Tipo
    public void nuevoTipo()
    {
        boolean entradavalida = false;
        while(!entradavalida)
        {
            try
            {
                String nuevoTipo = JOptionPane.showInputDialog(null,"Digita el nuevo tipo: ");
                invocar.setTipo(nuevoTipo);
                mensaje.log(Level.INFO,"Tipo modificado satisfactoriamente, tu nuevo tipo es: {0}", new Object[]{invocar.getTipo()});
                entradavalida = true;
            }
            catch (Exception e)
            {
                mensaje.warning("Solo se aceptan letras NO numeros " + e.getMessage());
            }
        }
    }
}
