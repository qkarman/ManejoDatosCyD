package aplicacion.menu;
import aplicacion.datos.EnemigosDao;
import aplicacion.datos.IEnemigosDao;

import javax.swing.JOptionPane;

public class MenuCrud
{
    //Creamos nuestra variable global
    protected int activador;

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
                    case 5 -> System.out.println("Hola");
                    case 6 -> JOptionPane.showMessageDialog(null,"Nos vemos pronto Capitan");
                    default -> System.out.println("Escribiste una opcion incorrecta");
                }
            }
            catch(Exception n)
            {
                System.out.println("Estas digitando mal las opciones marcadas");
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
            //Mostramos todos los enemigos
            enemigos.forEach(enemigo -> System.out.println(enemigo));
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
            System.out.println("Error al crear el enemigo" + e.getMessage());
        }
    }

    //*Creamos el metodo para modificar enemigo
    public void modificarEnemigo()
    {
        try
        {
            int busquedaId = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el id del enemigo a modificar"));
            AtributosEnemigos invocar = new AtributosEnemigos();
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
                    case 1 ->
                    {
                        boolean entradaValida = false;
                        while(!entradaValida)
                        {
                            try
                            {
                                int nuevoNivel = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el nuevo nivel del enemigo: "));

                                    invocar.setNivel(nuevoNivel);
                                    System.out.println("Nivel modificado satisfactoriamente, tu nuevo nivel es: " + invocar.getNivel());
                                    entradaValida = true;
                            }
                            catch(NumberFormatException e)
                            {
                                System.out.println("Solo se aceptan numeros enteros: " + e.getMessage());
                            }
                        }
                    }
                    case 2 ->
                    {
                        boolean entradavalida = false;
                        while(!entradavalida)
                        {
                            try
                            {
                                int nuevoAtaque = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el nuevo ataque del enemigo: "));
                                invocar.setAtaque(nuevoAtaque);
                                System.out.println("Ataque modificado satisfactoriamente, tu nuevo ataque es: " + invocar.getAtaque());
                                entradavalida = true;
                            }
                            catch(NumberFormatException e)
                            {
                                System.out.println("Solo se aceptan numeros enteros" + e.getMessage());
                            }
                        }

                    }
                    case 3 ->
                    {
                        int nuevaVida = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita la nueva vida del enemigo: "));
                        invocar.setVida(nuevaVida);
                        System.out.println("Vida modificada satisfactoriamente, tu nueva vida es: " + invocar.getVida());
                    }
                    case 4 ->
                    {
                        String nuevoNombre = JOptionPane.showInputDialog(null,"Digita el nuevo nombre: ");
                        invocar.setNombre(nuevoNombre);
                        System.out.println("Nombre modificado satisfactoriamente, tu nuevo nombre es: " + invocar.getVida());
                    }
                    case 5 ->
                    {
                        String nuevaDebilidad = JOptionPane.showInputDialog(null,"Digita la nueva debilidad: ");
                        invocar.setDebilidad(nuevaDebilidad);
                        System.out.println("Debilidad modificada satisfactoriamente, tu nueva debilidad es: " + invocar.getDebilidad());
                    }
                    case 6 ->
                    {
                        String nuevoTipo = JOptionPane.showInputDialog(null,"Digita el nuevo tipo: ");
                        invocar.setTipo(nuevoTipo);
                        System.out.println("Tipo modificado satisfactoriamente, tu nuevo tipo es : " + invocar.getTipo());
                    }
                    case 7 -> System.out.println("Nos vemos jefe");

                    default -> System.out.println("Digitaste algo mal");
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
            System.out.println("Error en: " + e.getMessage());
        }
    }

    //*Creamos el metodo para eliminar un enemigo
    public void eliminarEnemigo()
    {
        try
        {
            int busquedaId = Integer.parseInt(JOptionPane.showInputDialog(null,"Digita el id del enemigo a eliminar: "));

            AtributosEnemigos invocar = new AtributosEnemigos();
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
            System.out.println("Error en: " + e.getMessage());
        }
    }
}
