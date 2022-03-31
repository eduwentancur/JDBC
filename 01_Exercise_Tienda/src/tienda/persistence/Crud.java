package tienda.persistence;

import java.sql.SQLException;
import java.util.List;

public interface Crud<E, Codigo> {
    
    void create(E entity)throws Exception;
    
    void update(E entity)throws Exception;
    
    void deleteById(Codigo Codigo)throws Exception;
    
    E findById(Codigo id)throws Exception;
    
    List<E> findAll()throws Exception;
    
    E findOne() throws SQLException;
}
