package tienda.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.entity.Fabricante;
import tienda.entity.Producto;

public class ProductoDAO extends DAO implements Crud<Producto, Integer> {

    @Override
    public void create(Producto producto) throws Exception {
        String tamplate = "INSERT INTO producto VALUES (NULL, '%s', '%s', '%s');";
        String sql = String.format(tamplate, producto.getNombre(), producto.getPrecio(), producto.getCodigo_fabricante().getCodigo());
        insertModifyDelete(sql);
    }

    @Override
    public void update(Producto producto) throws Exception {
        String template = "UPDATE producto SET nombre = '%s', precio = '%s', codigo_fabricante = '%s' WHERE codigo = '%s';";
        String sql = String.format(template, producto.getNombre(), producto.getPrecio(), producto.getCodigo_fabricante().getCodigo(), producto.getCodigo());
        insertModifyDelete(sql);
    }

    @Override
    public void deleteById(Integer codigo) throws Exception {
        String sql = "DELETE FROM producto WHERE codigo = " + codigo + ";";
        insertModifyDelete(sql);
    }

    @Override
    public Producto findById(Integer codigo) throws Exception {
        String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo WHERE p.codigo = " + codigo + ";";
        queryDatabase(sql);
        while (resultSet.next()) {
            return findOne();
        }
        return null;
    }

    @Override
    public List<Producto> findAll() throws Exception {
        String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo;";
        queryDatabase(sql);
        List<Producto> productos = new ArrayList<>();
        while (resultSet.next()) {
            productos.add(findOne());
        }
        return productos;
    }

    @Override
    public Producto findOne() throws SQLException {
        Producto producto = new Producto();
        producto.setCodigo(resultSet.getInt(1));
        producto.setNombre(resultSet.getString(2));
        producto.setPrecio(resultSet.getDouble(3));
        Fabricante fabricante = new Fabricante();
        fabricante.setCodigo(resultSet.getInt(4));
        fabricante.setNombre(resultSet.getString(5));
        producto.setCodigo_fabricante(fabricante);
        return producto;
    }

    public List<Producto> listaPorPrecio() throws Exception {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo WHERE p.precio >=120 AND p.precio <=202 ;";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            while (resultSet.next()) {
                productos.add(findOne());
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }
    }

    public List<Producto> buscarPortatiles() throws Exception {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo WHERE p.nombre LIKE '%Portatil%'";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            while (resultSet.next()) {
                productos.add(findOne());
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }
    }

    public List<Producto> productoBarato() throws Exception {
        try {
            String sql = "SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante=f.codigo ORDER BY p.precio ASC LIMIT 1;";
            queryDatabase(sql);
            List<Producto> productos = new ArrayList<>();
            while (resultSet.next()) {
                productos.add(findOne());
            }
            return productos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los productos");
        }
    }
}
