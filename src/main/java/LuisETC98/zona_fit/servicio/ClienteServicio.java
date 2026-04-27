package LuisETC98.zona_fit.servicio;

import LuisETC98.zona_fit.Datos.ClienteRepositorio;
import LuisETC98.zona_fit.modelo.Cliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service //Anotación que se agrega a clase que contiene lógica de negocio (como ClienteServicio)
@Validated //Activa validación de la clase
public class ClienteServicio implements IClienteServicio{ //implementación de métodos de interface IClienteServicio
    @Autowired //Inyecta dependencias de capa de datos
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes(){
        return (List<Cliente>) clienteRepositorio.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente){
        return clienteRepositorio.findById(idCliente).orElse(null);
    }

    @Override
    public void guardarCliente(@Valid Cliente cliente){
        clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente){
        clienteRepositorio.delete(cliente);
    }
}
