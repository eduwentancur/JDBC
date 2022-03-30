package tienda.persistence;

import java.util.ArrayList;
import java.util.List;
import tienda.entity.Fabricante;
import tienda.entity.Producto;

public class ProductoDAO extends DAO{
    
    private FabricanteDAO fabricanteDAO;

    public ProductoDAO() {
        this.fabricanteDAO = new FabricanteDAO();
    }
    
    public void saveProducto(Producto producto) throws Exception{
        try {
            if(producto == null){
                throw new Exception("El producto no debe estar nulo");
            }
            String tamplate = "INSERT INTO producto VALUES (NULL, '%s', '%s', '%s');";
            String sql = String.format(tamplate,producto.getNombre(),producto.getPrecio(),producto.getCodigo_fabricante().getCodigo());
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

            String template = "UPDATE producto SET nombre = '%s', precio = '%s', codigo_fabricante = '%s' WHERE codigo = '%s';";
            String sql = String.format(template,producto.getNombre(),producto.getPrecio(),producto.getCodigo_fabricante().getCodigo(),producto.getCodigo());

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar un producto");
        }
    }
    
    public List<Producto> getProducto() throws Exception {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo;";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            while (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante fabricante = fabricanteDAO.buscarPorCodigoFabricante(codigo_fabricante);
                producto.setCodigo_fabricante(fabricante);
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        } 
    }
    
    public List<Producto> listaPorPrecio()throws Exception{
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo WHERE p.precio >=120 AND p.precio <=202 ;";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            while (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante fabricate = fabricanteDAO.buscarPorCodigoFabricante(codigo_fabricante);
                producto.setCodigo_fabricante(fabricate);
                
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }
    }

    public List<Producto> buscarPortatiles() throws Exception{
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo WHERE p.nombre LIKE '%Portatil%'";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            Producto producto;
            while (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante fabricante = fabricanteDAO.buscarPorCodigoFabricante(codigo_fabricante);
                producto.setCodigo_fabricante(fabricante);
                productos.add(producto);
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }
    }
    
    public List<Producto> productoBarato() throws Exception{
        try {
            String sql = "SELECT p.nombre, p.precio FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo ORDER BY p.precio ASC LIMIT 1;";
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
        }
    }
    
    public Producto buscarProductoId(Integer codigo) throws Exception {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo WHERE p.codigo = " + codigo + ";";
            queryDatabase(sql);
            Producto producto = null;
            while (resultSet.next()) {
                producto = new Producto();
                producto.setCodigo(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setPrecio(resultSet.getDouble(3));
                Integer codigo_fabricante = resultSet.getInt(4);
                Fabricante fabricante = fabricanteDAO.buscarPorCodigoFabricante(codigo_fabricante);
                producto.setCodigo_fabricante(fabricante);
            }
            return producto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al buscar");
        }
    }
}