package dtos;

public class ProductNodeDTO {
    private Long id;
    private String name;
    private String category;
    private int year;
    private String director;

    public ProductNodeDTO(Long id, String name, String category, int year, String director) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.year = year;
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
