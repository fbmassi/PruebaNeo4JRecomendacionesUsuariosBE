package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductNodeDTO {
    private Long id;
    private String name;
    private String category;
    private int year;
    private String director;
}