package entities;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
public class CustomerNode {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    // ... otras propiedades

    @Relationship(type = "FAVORITO_DE")
    private Set<ProductNode> favoritos = new HashSet<>();
}
