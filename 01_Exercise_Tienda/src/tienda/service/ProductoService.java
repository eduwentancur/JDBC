package tienda.service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import tienda.entity.Producto;
import tienda.persistence.FabricanteDAO;
import tienda.persistence.ProductoDAO;


public class ProductoService {

    ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
        
    }
    
    public void mostrarProductos() throws Exception{
        try {
            List<Producto> productos = productoDAO.getProducto();

            if (productos.isEmpty()) {
                throw new Exception("No existen personas");
            } else {
                System.out.println("LISTA DE PRODUCTOS");
                System.out.printf("%-10s%-35s%-15s%-20s\n", "codigo", "nombre", "precio", "codigo_fabricante");
                for (Producto pro : productos) {
                    System.out.printf("%-10s%-35s%-15s%-20s\n", pro.getCodigo(), pro.getNombre(),pro.getPrecio(), pro.getCodigo_fabricante());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listaNombresProductos() throws Exception {
        try {
            List<Producto> productos = productoDAO.getProducto();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos!");
            } else {
                System.out.println("LISTA DE LOS NOMBRE DE TODOS LOS PRODUCTOS\n");
                for (Producto pro : productos) {
                    System.out.printf("%-15s\n",pro.getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listaNombresYPrecios() throws Exception {
        try {
            List<Producto> productos = productoDAO.getProducto();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE LOS NOMBRES Y PRECIOS DE TODOS LOS PRODUCTOS\n");
                System.out.printf("%-40s%-20s\n", "nombre", "precio");
                for (Producto pro : productos) {
                    System.out.printf("%-40s%-20s\n",pro.getNombre(),pro.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void productosEntre120Y202()throws Exception{
        try {
            List<Producto> productos = productoDAO.listaPorPrecio();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE TODOS LOS PRODUCTOS ENTRE 120 Y 202\n");
                System.out.printf("%-10s%-35s%-20s%-20s\n","codigo","nombre", "precio","codigo_fabricante");
                for (Producto pro : productos) {
                    System.out.printf("%-10s%-35s%-20s%-20s\n",pro.getCodigo(),pro.getNombre(),pro.getPrecio(),pro.getCodigo_fabricante());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void buscarPortatiles() throws Exception{
        try {
            List<Producto> productos = productoDAO.buscarPortatiles();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE TODOS LOS PORTATILES\n");
                System.out.printf("%-10s%-35s%-20s%-20s\n","codigo","nombre", "precio","codigo_fabricante");
                for (Producto pro : productos) {
                    System.out.printf("%-10s%-35s%-20s%-20s\n",pro.getCodigo(),pro.getNombre(),pro.getPrecio(),pro.getCodigo_fabricante());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void productoMasBarato() throws Exception{
        try {
            List<Producto> productos = productoDAO.productoBarato();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("EL PRODUCTO MAS BARATO\n");
                System.out.printf("%-30s%-20s\n","nombre", "precio");
                for (Producto pro : productos) {
                    System.out.printf("%-30s%-20s\n",pro.getNombre(),pro.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void ingresarProducto() throws Exception{
        try {
            Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
            System.out.println("Ingrese nombre del producto");
            String nombre = read.next();
            System.out.println("Ingrese precio del producto");
            Double precio = read.nextDouble();
            System.out.println("Ingrese codigo del fabricante");
            Integer codigo_fabricante = read.nextInt();
            crearProducto(nombre,precio,codigo_fabricante);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void crearProducto(String nombre, Double precio, Integer codigo_fabricante) throws Exception{
        FabricanteDAO fabricante =new FabricanteDAO();
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }
            if (precio == null || precio <0){
                throw new Exception("El precio no puede estar vacio o ser negativo");
            }
            if (codigo_fabricante == null || fabricante.buscarPorCodigoFabricante(codigo_fabricante)==null) {
                throw new Exception("El codigo del fabricante no existe o esta nulo");
            }
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigo_fabricante(codigo_fabricante);
            productoDAO.saveProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void editarProducto() throws Exception{
        try {
            Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
            mostrarProductos();
            System.out.println("Ingrese el codigo del producto que desea modificar");
            Integer codigo=read.nextInt();
            System.out.println("Ingrese nuevo nombre");
            String nombre = read.next();
            System.out.println("Ingrese el nuevo precio");
            Double precio = read.nextDouble();
            System.out.println("Ingrese el codigo del fabricante");
            Integer codigo_fabricante = read.nextInt();
            Producto producto = new Producto();
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigo_fabricante(codigo_fabricante);
            productoDAO.modifyProducto(producto);
            mostrarProductos();
        } catch (Exception e) {
            throw e;
        }
    }
}
