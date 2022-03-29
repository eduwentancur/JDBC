
package tienda.persistence;

import java.util.ArrayList;
import java.util.List;
import tienda.entity.Producto;


public class ProductoDAO extends DAO{

    
    public void saveProducto(Producto producto) throws Exception{
        try {
            if(producto == null){
                throw new Exception("El producto no debe estar nulo");
            }
            
            String tamplate = "INSERT INTO producto VALUES (NULL, '%s', '%s', '%s');";
            String sql = String.format(tamplate,producto.getNombre(),producto.getPrecio(),producto.getCodigo_fabricante());
            insertModifyDelete(sql);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modifyProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("El fabricante no puede ser nula");
            }

            String template = "UPDATE producto SET nombre = '%s', precio = %s, codigo_fabricante = %s, WHERE codigo = %s;";
            String sql = String.format(template,producto.getNombre(),producto.getPrecio(),producto.getCodigo_fabricante(),producto.getCodigo());

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar un producto");
        }
    }
    
    public void deleteProducto(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE codigo = " + codigo + ";";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar un producto");
        }
    }

    public List<Producto> getProducto() throws Exception {
        try {
            String sql = "SELECT * FROM producto;";

            queryDatabase(sql);

            List<Producto> productos = new ArrayList<>();
            Producto producto;

            while (resultSet.next()) {
                producto = new Producto();
                
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                producto.setCodigo_fabricante(resultSet.getInt(4));
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        } finally {
            disconnectDatabase();
        }
    }
    
    public List<Producto> listaPorPrecio()throws Exception{
        try {
            String sql = "SELECT * FROM producto WHERE precio >=120 AND precio <=202 ;";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            while (resultSet.next()) {
                producto = new Producto();
                
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                producto.setCodigo_fabricante(resultSet.getInt(4));
                productos.add(producto);
            }
            return productos;
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }finally {
            disconnectDatabase();
        }
    }

    public List<Producto> buscarPortatiles() throws Exception{
        try {
            String sql = "SELECT * FROM producto WHERE nombre LIKE '%Portatil%'";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            while (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                producto.setCodigo_fabricante(resultSet.getInt(4));
                productos.add(producto);
                
            }
            return productos;
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }finally {
            disconnectDatabase();
        }
    
    }
    
    public List<Producto> productoBarato() throws Exception{
        try {
            String sql = "SELECT nombre, precio FROM producto ORDER BY precio ASC LIMIT 1;";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            while (resultSet.next()) {
                producto = new Producto();
                producto.setNombre(resultSet.getString(1));
                producto.setPrecio(resultSet.getDouble(2));
                productos.add(producto);
                
            }
            return productos;
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }finally {
            disconnectDatabase();
        }
    }
    
}
