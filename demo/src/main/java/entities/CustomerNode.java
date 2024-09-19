package entities;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class CustomerNode {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    @Relationship(type = "FAVORITO")
    private Set<ProductNode> favoritos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<ProductNode> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<ProductNode> favoritos) {
        this.favoritos = favoritos;
    }
}
