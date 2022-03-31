package tienda.entity;

public class Producto {
    
    private int codigo;
    private String nombre;
    private Double precio;
    private Fabricante codigo_fabricante;

    public Producto() {
    }

    public Producto(int codigo, String nombre, Double precio, Fabricante codigo_fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo_fabricante = codigo_fabricante;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Fabricante getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(Fabricante codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }

    @Override
    public String toString() {
        return "Producto:\n" + "codigo= " 
                + codigo + ", nombre= " 
                + nombre + ", precio= " 
                + precio + ", codigo_fabricante= " 
                + codigo_fabricante.getCodigo() + '}';
    }
}
