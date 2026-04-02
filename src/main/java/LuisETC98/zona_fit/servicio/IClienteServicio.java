package LuisETC98.zona_fit.servicio;

import LuisETC98.zona_fit.modelo.Cliente;
import java.util.List;

public interface IClienteServicio {
    List<Cliente> listarClientes();
    Cliente buscarClientePorId(Integer idCliente);
    void guardarCliente(Cliente cliente);
    void eliminarCliente(Cliente cliente);
}
