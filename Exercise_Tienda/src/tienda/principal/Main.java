package tienda.principal;

import tienda.service.Service;

public class Main {

    public static void main(String[] args) throws Exception {
        Service service = new Service();
        service.menu();
    }

}
