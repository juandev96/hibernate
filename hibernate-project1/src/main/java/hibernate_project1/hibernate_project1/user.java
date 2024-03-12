package hibernate_project1.hibernate_project1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity // Indica que esta clase es una entidad mapeada a una tabla en la base de datos
@Table(name = "user") // Especifica el nombre de la tabla en la base de datos
public class user {
    
    @Id // Indica que este campo es la clave primaria de la tabla
    @Column(name="id") // Especifica el nombre de la columna en la base de datos
    private int id; // Campo que representa el ID del usuario
    
    @Column(name="name") // Especifica el nombre de la columna en la base de datos
    private String name; // Campo que representa el nombre del usuario

    //Constructor vacío necesario para JPA
    public user() {
    }
    
    // Getters y setters para los campos de la entidad
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
