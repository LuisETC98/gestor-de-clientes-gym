package LuisETC98.zona_fit.controlador;

import LuisETC98.zona_fit.modelo.Cliente;
import LuisETC98.zona_fit.servicio.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.validation.ConstraintViolationException;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ViewScoped //Posibilita que controlador esté activo mientras usuario utiliza interfaz (ciclo de vida)
public class IndexControlador {
    @Autowired //Inyección de dependencia

    //Atributos
    IClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;


    @PostConstruct //Ejecuta método después de inyección de dependencias
    public void iniciar(){
        cargarDatos();
    }

    public void cargarDatos(){ //inicializa clientes
        this.clientes = this.clienteServicio.listarClientes();
        this.clientes.forEach(System.out::println);
    }

    public void agregarCliente(){
        this.clienteSeleccionado = new Cliente();
    }


    public void guardarCliente(){
        try {
            //Agregar
            if(this.clienteSeleccionado.getId() == null){
                this.clienteServicio.guardarCliente(this.clienteSeleccionado);
                this.clientes.add(this.clienteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cliente Agregado"));
            }
            else { //Modificar registro
                this.clienteServicio.guardarCliente(this.clienteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cliente Actualizado"));
            }
            //Ocultar la ventana modal
            PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");

            //Actualizar la tabla y mensajes usando ajax
            PrimeFaces.current().ajax().update("forma-clientes:mensajes",
                    "forma-clientes:clientes-tabla");

            //Reset del objeto cliente seleccionado
            this.clienteSeleccionado = null;

        } catch(ConstraintViolationException e) {
            //Se muestra el siguiente mensaje de error en la interfaz
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validación", "Revise los datos del formulario"));
            //Se actualiza solo el componente de mensajes en la vista
            PrimeFaces.current().ajax().update("forma-clientes:mensajes");
            /*Nota: No se limpia 'clienteSeleccionado' ni cerramos la ventana modal
             para que el usuario pueda corregir sus datos sin volver a escribir todo.*/
        }
    }

    public void eliminarCliente(){
        this.clienteServicio.eliminarCliente(this.clienteSeleccionado);
        //Eliminamos el registro de la lista de clientes
        this.clientes.remove(this.clienteSeleccionado);
        //Reset del objeto de cliente seleccionado
        this.clienteSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cliente Eliminado"));
        PrimeFaces.current().ajax().update("forma-clientes:mensajes",
                "forma-clientes:clientes-tabla");
    }

    //Métodos get y set
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public IClienteServicio getClienteServicio() {
        return clienteServicio;
    }

    public void setClienteServicio(IClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
}
