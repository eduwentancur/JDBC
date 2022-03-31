package tienda.service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import tienda.entity.Fabricante;
import tienda.entity.Producto;
import tienda.persistence.FabricanteDAO;
import tienda.persistence.ProductoDAO;

public class ProductoService {

    ProductoDAO productoDAO;
    FabricanteDAO fabricanteDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
        this.fabricanteDAO = new FabricanteDAO();
    }

    public void mostrarProductos() throws Exception {
        try {
            List<Producto> productos = productoDAO.findAll();

            if (productos.isEmpty()) {
                throw new Exception("No existen personas");
            } else {
                System.out.println("LISTA DE PRODUCTOS");
                System.out.printf("%-10s%-35s%-15s%-20s\n", "codigo", "nombre", "precio", "codigo_fabricante");
                for (Producto pro : productos) {
                    System.out.printf("%-10s%-35s%-15s%-20s\n", pro.getCodigo(), pro.getNombre(), pro.getPrecio(), pro.getCodigo_fabricante().getCodigo());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void listaNombresProductos() throws Exception {
        try {
            List<Producto> productos = productoDAO.findAll();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos!");
            } else {
                System.out.println("LISTA DE LOS NOMBRE DE TODOS LOS PRODUCTOS\n");
                for (Producto pro : productos) {
                    System.out.printf("%-15s\n", pro.getNombre());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void listaNombresYPrecios() throws Exception {
        try {
            List<Producto> productos = productoDAO.findAll();

            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE LOS NOMBRES Y PRECIOS DE TODOS LOS PRODUCTOS\n");
                System.out.printf("%-40s%-20s\n", "nombre", "precio");
                for (Producto pro : productos) {
                    System.out.printf("%-40s%-20s\n", pro.getNombre(), pro.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void productosEntrePrecios() throws Exception {
        try {
            List<Producto> productos = productoDAO.listaPorPrecio();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE TODOS LOS PRODUCTOS ENTRE 120 Y 202\n");
                System.out.printf("%-10s%-35s%-20s%-20s\n", "codigo", "nombre", "precio", "codigo_fabricante");
                for (Producto pro : productos) {
                    System.out.printf("%-10s%-35s%-20s%-20s\n", pro.getCodigo(), pro.getNombre(), pro.getPrecio(), pro.getCodigo_fabricante().getCodigo());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void mostrarPortatiles() throws Exception {
        try {
            List<Producto> productos = productoDAO.buscarPortatiles();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("LISTA DE TODOS LOS PORTATILES\n");
                System.out.printf("%-10s%-35s%-20s%-20s\n", "codigo", "nombre", "precio", "codigo_fabricante");
                for (Producto pro : productos) {
                    System.out.printf("%-10s%-35s%-20s%-20s\n", pro.getCodigo(), pro.getNombre(), pro.getPrecio(), pro.getCodigo_fabricante().getCodigo());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void productoMasBarato() throws Exception {
        try {
            List<Producto> productos = productoDAO.productoBarato();
            if (productos.isEmpty()) {
                throw new Exception("No existen productos");
            } else {
                System.out.println("EL PRODUCTO MAS BARATO\n");
                System.out.printf("%-30s%-20s\n", "nombre", "precio");
                for (Producto pro : productos) {
                    System.out.printf("%-30s%-20s\n", pro.getNombre(), pro.getPrecio());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void ingresarProducto() throws Exception {
        try {
            FabricanteService f = new FabricanteService();
            Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
            System.out.println("Ingrese nombre del producto");
            String nombre = read.next();
            System.out.println("Ingrese precio del producto");
            Double precio = read.nextDouble();
            f.mostrarFabricantes();
            System.out.println("Ingrese codigo del fabricante que este en la lista");
            Integer codigo_fabricante = read.nextInt();
            Fabricante fabricante = fabricanteDAO.findById(codigo_fabricante);
            crearProducto(nombre, precio, fabricante);
        } catch (Exception e) {
            System.out.println("ERROR!!! EL CODIGO DEL FABRICANTE NO EXISTE "+e);
        }
    }

    public void crearProducto(String nombre, Double precio, Fabricante fabricante) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }
            if (precio == null || precio < 0) {
                throw new Exception("El precio no puede estar vacio o ser negativo");
            }
            if (fabricante == null || fabricanteDAO.findById(fabricante.getCodigo()) == null) {
                throw new Exception("El codigo del fabricante no existe o esta nulo");
            }
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigo_fabricante(fabricante);
            productoDAO.create(producto);
        } catch (Exception e) {
            throw e;
        }
    }

    public void editarProducto() throws Exception {
        try {
            Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
            mostrarProductos();
            System.out.println("Ingrese el codigo del producto que desea modificar");
            Integer codigo = read.nextInt();
            Producto producto = productoDAO.findById(codigo);
            try {
                System.out.println(producto.toString());
                int opcion;
                do {
                    System.out.println("QUE DESEA CAMBIAR?");
                    System.out.println("1-nombre del producto");
                    System.out.println("2-precio del producto");
                    System.out.println("3-codigo del fabricante");
                    System.out.println("4-YA NO DESEO CAMBIAR NADA");
                    opcion = read.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese nombre");
                            String nombre = read.next();
                            producto.setNombre(nombre);
                            break;
                        case 2:
                            System.out.println("Ingrese precio");
                            Double precio = read.nextDouble();
                            producto.setPrecio(precio);
                            break;
                        case 3:
                            System.out.println("Ingrese codigo del fabricante");
                            Integer codigo_fabricante = read.nextInt();
                            Fabricante fabricante = fabricanteDAO.findById(codigo_fabricante);
                            producto.setCodigo_fabricante(fabricante);
                            break;
                        case 4:
                            System.out.println("ADIOS!!");
                            break;
                        default:
                            System.out.println("Ingreso opcion icorrecta");
                            break;
                    }
                } while (opcion != 4);
            } catch (Exception e) {
                throw new Exception ("Error al editar un producto "+e);
            }
            productoDAO.update(producto);
            mostrarProductos();
        } catch (Exception e) {
            System.out.println("ERROR!!! NO SE ENCONTRO EL PRODUCTO O EL CODIGO DEL FABRICANTE NO EXISTE "+e);
        }
    }
}
