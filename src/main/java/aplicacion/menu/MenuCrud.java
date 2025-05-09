package aplicacion.menu;
import javax.swing.JOptionPane;

public class MenuCrud
{
    //Creamos nuestra variable global
    protected int activador;

    //*Creamos la funcion main para hacer pruebas
    public static void main(String[] args)
    {
        MenuCrud invocar = new MenuCrud();
        invocar.menu();
    }

    //*Creamos la funcion del menu
    public void menu()
    {
        while(activador != 5)
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
                        5.- Salir
                        Selecciona una opcion:
                        """));

                switch (activador)
                {
                    case 1 -> System.out.println();
                    case 2 -> System.out.println();
                    default -> System.out.println("Escribiste una opcion incorrecta");
                }
            }
            catch(Exception n)
            {
                System.out.println("Estas digitando mal las opciones marcadas");
            }
        }
    }

    public void listarEnemigos()
    {

    }
}
