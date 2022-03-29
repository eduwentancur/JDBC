
package estancias.entidades;


public class Familia {
    private Integer id_familia;
    private String nombre;
    private Integer edad_minima;
    private Integer edad_maxima;
    private Integer num_hijos;
    private String email;
    private Casa casa;

    public Familia() {
    }

    public Familia(Integer id_familia, String nombre, Integer edad_minima, Integer edad_maxima, Integer num_hijos, String email, Casa casa) {
        this.id_familia = id_familia;
        this.nombre = nombre;
        this.edad_minima = edad_minima;
        this.edad_maxima = edad_maxima;
        this.num_hijos = num_hijos;
        this.email = email;
        this.casa = casa;
    }

    public Integer getId_familia() {
        return id_familia;
    }

    public void setId_familia(Integer id_familia) {
        this.id_familia = id_familia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad_minima() {
        return edad_minima;
    }

    public void setEdad_minima(Integer edad_minima) {
        this.edad_minima = edad_minima;
    }

    public Integer getEdad_maxima() {
        return edad_maxima;
    }

    public void setEdad_maxima(Integer edad_maxima) {
        this.edad_maxima = edad_maxima;
    }

    public Integer getNum_hijos() {
        return num_hijos;
    }

    public void setNum_hijos(Integer num_hijos) {
        this.num_hijos = num_hijos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }
    
}
