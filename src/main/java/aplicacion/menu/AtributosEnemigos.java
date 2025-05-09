package aplicacion.menu;

public class AtributosEnemigos
{
    private int id, nivel, ataque, vida;
    private String nombre, debilidad, tipo;

    public AtributosEnemigos()
    {

    }

    public AtributosEnemigos(int id, int nivel, int ataque, int vida, String nombre, String debilidad, String tipo)
    {
        this.id = id;
        this.nivel = nivel;
        this.ataque = ataque;
        this.vida = vida;
        this.nombre = nombre;
        this.debilidad = debilidad;
        this.tipo = tipo;
    }

    public AtributosEnemigos(int nivel, int ataque, int vida, String nombre, String debilidad, String tipo)
    {
        this.nivel = nivel;
        this.ataque = ataque;
        this.vida = vida;
        this.nombre = nombre;
        this.debilidad = debilidad;
        this.tipo = tipo;
    }



    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getNivel()
    {
        return nivel;
    }

    public void setNivel(int nivel)
    {
        this.nivel = nivel;
    }

    public int getAtaque()
    {
        return ataque;
    }

    public void setAtaque(int ataque)
    {
        this.ataque = ataque;
    }

    public int getVida()
    {
        return vida;
    }

    public void setVida(int vida)
    {
        this.vida = vida;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDebilidad()
    {
        return debilidad;
    }

    public void setDebilidad(String debilidad)
    {
        this.debilidad = debilidad;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    @Override
    public String toString()
    {
        return "AtributosEnemigos{" +
                "id=" + id +
                ", nivel=" + nivel +
                ", ataque=" + ataque +
                ", vida=" + vida +
                ", nombre='" + nombre + '\'' +
                ", debilidad='" + debilidad + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
