package tienda.service;

import java.util.Locale;
import java.util.Scanner;
import tienda.entity.Fabricante;
import tienda.persistence.FabricanteDAO;

public class FabricanteService {

    FabricanteDAO fabricanteDAO;
    
    public FabricanteService() {
        this.fabricanteDAO = new FabricanteDAO();
    }

    public void ingresarFabricante() throws Exception {
        try {
            Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
            System.out.println("Ingrese nombre del nuevo fabricante");
            String nombre = read.next();
            createFabricante(nombre);
        } catch (Exception e) {
            throw e;
        }
    }

    public void createFabricante(String nombre) throws Exception {
        try {
            if (nombre==null || nombre.trim().isEmpty()) {
                throw new Exception("Error, el nombre no puede estar nulo");
            }
            
            Fabricante fabricanteNuevo = new Fabricante();
            fabricanteNuevo.setNombre(nombre);
            fabricanteDAO.saveFabricante(fabricanteNuevo);
            
        } catch (Exception e) {
            throw e;
        }
    }
}
