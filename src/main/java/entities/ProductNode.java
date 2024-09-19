package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class ProductNode {
    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private Long id;
    private String name;
    private String category;
    private int year;
    private String director;
}