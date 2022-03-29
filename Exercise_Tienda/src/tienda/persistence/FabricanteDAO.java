
package tienda.persistence;

import java.util.ArrayList;
import java.util.List;
import tienda.entity.Fabricante;


public class FabricanteDAO extends DAO{
    
    public void saveFabricante(Fabricante fabricante) throws Exception{
        try {
            if(fabricante == null){
                throw new Exception("El fabricante no debe ser nulo");
            }
            
            String tamplate = "INSERT INTO fabricante VALUES (NULL, '%s');";
            String sql = String.format(tamplate,fabricante.getNombre());
            insertModifyDelete(sql);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modifyFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("El fabricante no puede ser nula");
            }

            String template = "UPDATE fabricante SET nombre = '%s', WHERE codigo = %s;";
            String sql = String.format(template,fabricante.getNombre(),fabricante.getCodigo() );

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al modificar un fabricante");
        }
    }
    
    public void deleteFabricante(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM fabricante WHERE codigo = " + codigo + ";";

            insertModifyDelete(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al eliminar un fabricante");
        }
    }

    public List<Fabricante> getFabricante() throws Exception {
        try {
            String sql = "SELECT * FROM fabricante;";

            queryDatabase(sql);

            List<Fabricante> fabricantes = new ArrayList<>();
            Fabricante fabricante;

            while (resultSet.next()) {
                fabricante = new Fabricante();

                fabricante.setCodigo(resultSet.getInt(1));
                fabricante.setNombre(resultSet.getString(2));

                fabricantes.add(fabricante);
            }
            return fabricantes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Error al obtener los fabricantes");
        } finally {
            disconnectDatabase();
        }
    }
    
}
