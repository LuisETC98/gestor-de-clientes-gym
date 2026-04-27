package LuisETC98.zona_fit.modelo;

import jakarta.persistence.*;

// Importaciones necesarias:
 import jakarta.validation.constraints.Email;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.NotNull;


@Entity //Anotación que permie crear una tabla en base de datos relacional basada en esta clase
@Table(name = "cliente") //Permite especificar el nombre de la tabla

public class Cliente {
    @Id //Convierte variable id en llave primaria de tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Asigna automáticamente id a nuevo objeto creado (registro en tabla) y lo autoincrementa
    private Integer id;

    //Atributos (columnas) con validaciones Jakarta Bean Validation
    @NotBlank(message = "Ingresa un nombre")
    private String nombre;

    @NotBlank(message = "Ingresa un apellido")
    private String apellido;

    @NotBlank(message = "Ingresa un email")
    @Email(message = "Formato de correo no válido")
    private String email;

    @NotNull(message = "Ingresa una membresía")
    private Integer membresia;


    //Constructor vacío
    public Cliente(){
    }
    //Constructor con todos los atributos (menos id)
    public Cliente(String nombre, String apellido, String email, Integer membresia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.membresia = membresia;
    }
    //Constructor con todos los atributos
    public Cliente(Integer id, String nombre, String apellido, String email, Integer membresia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.membresia = membresia;
    }

    //Métodos get y set (encapsulamiento)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getMembresia() {
        return membresia;
    }
    public void setMembresia(Integer membresia) {
        this.membresia = membresia;
    }
    //Sobreescritura de método ToString

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", membresía=" + membresia +
                '}';
    }
}
