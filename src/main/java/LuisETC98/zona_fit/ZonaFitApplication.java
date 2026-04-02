package LuisETC98.zona_fit;

import LuisETC98.zona_fit.modelo.Cliente;
import LuisETC98.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

//Clase de presentación
//@SpringBootApplication
public class ZonaFitApplication {

    @Autowired
    private IClienteServicio clienteServicio;

	public static void main(String[] args) {
		var fabricaSpring = SpringApplication.run(ZonaFitApplication.class, args);
        System.out.println("Fabrica de Spring inicializada: " + fabricaSpring);
        var app = fabricaSpring.getBean(ZonaFitApplication.class);
        app.ejecutarMenu();
	}

    public void ejecutarMenu(){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do{
            System.out.println("\n*** Aplicación Zona Fit (GYM) ***");
            System.out.println("1. Listar clientes");
            System.out.println("2. Buscar cliente");
            System.out.println("3. Agregar cliente");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch(opcion){
                case 1 -> listarClientes();
                case 2 -> buscarClientePorId(sc);
                case 3 -> guardarCliente(sc);
                case 4 -> modificarCliente(sc);
                case 5 -> eliminarCliente(sc);
                case 6 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida");
            }
        }while(opcion != 6);

    }

    private void listarClientes(){
        System.out.println("\n--- Listado de Clientes ---");
        var clientes = clienteServicio.listarClientes();
        clientes.forEach(System.out::println);
    }

    private void buscarClientePorId(Scanner sc){
        System.out.println("\n--- Buscar cliente por Id --- \nId de cliente a buscar: ");
        int id = Integer.parseInt(sc.nextLine());
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if (cliente != null)
            System.out.println("Cliente encontrado: " + cliente);
        else
            System.out.println("Cliente no encontrado.");
    }

    private void guardarCliente(Scanner sc){
        System.out.println("\n--- Agregar cliente ---");
        Cliente cliente = leerDatosCliente(sc);
        clienteServicio.guardarCliente(cliente);
        System.out.println("Cliente guardado correctamente.");
    }

    private void modificarCliente (Scanner sc){
        System.out.println("\n--- Modificar cliente ---\nId de cliente a modificar");
        int id = Integer.parseInt(sc.nextLine());
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if (cliente != null) {
            System.out.println("Cliente a modificar: " + cliente);
            var clienteModificado = leerDatosCliente(sc);
            clienteModificado.setId(id);
            clienteServicio.guardarCliente(clienteModificado);
            System.out.println("Cliente ha sido modificado correctamente.");
        }else{
            System.out.println("Cliente no encontrado.");
        }

    }

    public void eliminarCliente(Scanner sc){
        System.out.println("\n--- Eliminar cliente ---\nId de cliente a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if(cliente != null){
            clienteServicio.eliminarCliente(cliente);
            System.out.println("Cliente eliminado correctamente.");
        }else{
            System.out.println("Cliente no encontrado.");
        }
    }

    private Cliente leerDatosCliente(Scanner sc){
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Membresía: ");
        int membresía = Integer.parseInt(sc.nextLine());
        return new Cliente(nombre, apellido, email, membresía);
    }

}
