package tienda.service;

import java.util.Locale;
import java.util.Scanner;
import tienda.persistence.FabricanteDAO;


public class Service {
   
    private Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
    
    ProductoService pro = new ProductoService();
    FabricanteService fabri = new FabricanteService();
    public void menu() throws Exception {
        
        Integer opcion=null;
        do {
            System.out.println("INGRESE OPCION\n"
                    + "1-Listar el nombre de todos los productos.\n"
                    + "2-Listar los nombres y los precios de todos los productos de la tabla producto.\n"
                    + "3-Listar aquellos productos que su precio este entre 120 y 202.\n"
                    + "4-Buscar y listar todos los Portatiles de la tabla producto.\n"
                    + "5-Listar el nombre y el precio del producto mas barato.\n"
                    + "6-Ingresar un producto a la base de datos.\n"
                    + "7-Ingresar un fabricante a la base de datos.\n"
                    + "8-Editar un producto con datos a eleccion.\n"
                    + "9-SALIR");
            opcion=read.nextInt();
            switch (opcion) {
                case 1:
                    pro.listaNombresProductos();
                    break;
                case 2:
                    pro.listaNombresYPrecios();
                    break;
                case 3:
                    pro.productosEntre120Y202();
                    break;
                case 4:
                    pro.buscarPortatiles();
                    break;
                case 5:
                    pro.productoMasBarato();
                    break;
                case 6:
                    pro.ingresarProducto();
                    break;
                case 7:
                    fabri.ingresarFabricante();
                    break;
                default:
                    throw new AssertionError();
            }
            
            
        } while (false);

    }

}
