package tienda.persistence;

import java.sql.SQLException;
import java.util.List;

public interface Crud<E, ID> {
    
    void ingresar(E entity)throws Exception;
    
    void modificar(E entity)throws Exception;
    
    void eliminarPorId(ID id)throws Exception;
    
    E buscarPorId(ID id)throws Exception;
    
    List<E> buscarTodo()throws Exception;
    
    E buscarUno() throws SQLException;
}
