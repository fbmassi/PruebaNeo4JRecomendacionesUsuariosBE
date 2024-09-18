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
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private Long id;
    private String name;
    private String category;
    private int views;
    private int year;
    private String director;

    @Relationship(type = "FAVORITO", direction = Relationship.Direction.INCOMING)
    private Set<CustomerNode> favoritos = new HashSet<>();

}