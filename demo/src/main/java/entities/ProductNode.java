package entities;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class ProductNode {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String category;
    private int year;
    private String director;

    @Relationship(type = "FAVORITO", direction = Relationship.Direction.INCOMING)
    private Set<CustomerNode> favoritos = new HashSet<>();

    public Set<CustomerNode> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<CustomerNode> favoritos) {
        this.favoritos = favoritos;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}