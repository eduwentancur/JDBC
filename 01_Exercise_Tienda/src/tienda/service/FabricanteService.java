package tienda.service;

import java.util.Locale;
import java.util.Scanner;
import tienda.entity.Fabricante;
import tienda.persistence.FabricanteDAO;


public class FabricanteService {
    
    FabricanteDAO fabricanteDAO;
    
    public void ingresarFabricante() throws Exception{
        Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
        try {
            String nombre;
            System.out.println("Ingrese nombre del fabricante");
            nombre = read.next();
            createFabricate(nombre);
            
            
            
        } catch (Exception e) {
        }
        
    }
     public void createFabricate(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            Fabricante fabricante = new Fabricante();

            fabricante.setNombre(nombre);
            
            fabricanteDAO.saveFabricante(fabricante);
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    
}
