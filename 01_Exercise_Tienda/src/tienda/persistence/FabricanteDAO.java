package tienda.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tienda.entity.Fabricante;

public class FabricanteDAO extends DAO implements Crud<Fabricante, Integer> {

    @Override
    public void create(Fabricante fabricante) throws Exception {
        String tamplate = "INSERT INTO fabricante VALUES (NULL,'%s');";
        String sql = String.format(tamplate, fabricante.getNombre());
        insertModifyDelete(sql);
    }

    @Override
    public void update(Fabricante fabricante) throws Exception {
        String template = "UPDATE fabricante SET nombre = '%s' WHERE codigo = %s;";
        String sql = String.format(template, fabricante.getNombre(), fabricante.getCodigo());
        insertModifyDelete(sql);
    }

    @Override
    public void deleteById(Integer codigo) throws Exception {
        String sql = "DELETE FROM fabricante WHERE codigo = " + codigo + ";";
        insertModifyDelete(sql);
    }

    @Override
    public Fabricante findById(Integer codigo) throws Exception {
        String sql = "SELECT * FROM fabricante WHERE codigo = " + codigo;
        queryDatabase(sql);
        while (resultSet.next()) {
            return findOne();
        }
        return null;
    }

    @Override
    public List<Fabricante> findAll() throws Exception {
        String sql = "SELECT * FROM fabricante;";
        queryDatabase(sql);
        List<Fabricante> fabricantes = new ArrayList<>();
        while (resultSet.next()) {
            fabricantes.add(findOne());
        }
        return fabricantes;
    }

    @Override
    public Fabricante findOne() throws SQLException {
        Fabricante fabricante = new Fabricante();
        fabricante.setCodigo(resultSet.getInt(1));
        fabricante.setNombre(resultSet.getString(2));
        return fabricante;
    }
}
