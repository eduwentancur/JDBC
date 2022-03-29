
package tienda.service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import tienda.entity.Producto;
import tienda.persistence.ProductoDAO;


public class ProductoService {

    ProductoDAO producto;

    public ProductoService() {
        this.producto = new ProductoDAO();
        
    }
    
    public void listaNombresProductos() throws Exception {
        try {
            List<Producto> productos = producto.getProducto();

            if (productos.isEmpty()) {
                throw new Exception("No existen personas");
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
            List<Producto> productos = producto.getProducto();

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
            List<Producto> productos = producto.listaPorPrecio();
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
            List<Producto> productos = producto.buscarPortatiles();
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
            List<Producto> productos = producto.productoBarato();
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
            Producto productoNuevo = new Producto();
            System.out.println("Ingrese nombre del producto");
            productoNuevo.setNombre(read.next());
            System.out.println("Ingrese precio del producto");
            productoNuevo.setPrecio(read.nextDouble());
            System.out.println("Ingrese codigo del fabricante");
            productoNuevo.setCodigo_fabricante(read.nextInt());
            producto.saveProducto(productoNuevo);
            listaNombresProductos();
            
        } catch (Exception e) {
            throw e;
        }
    }
    
}
