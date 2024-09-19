package services;

import dtos.ProductNodeDTO;
import entities.ProductNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProductNodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductNodeService {

    @Autowired
    private ProductNodeRepository productRepository;

    // Crear un nuevo producto
    public ProductNodeDTO createProduct(ProductNodeDTO productDTO) {
        ProductNode product = new ProductNode(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getCategory(),
                productDTO.getYear(),
                productDTO.getDirector()
        );
        ProductNode savedProduct = productRepository.save(product);
        return new ProductNodeDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getCategory(),
                savedProduct.getYear(),
                savedProduct.getDirector()
        );
    }

    // Obtener todos los productos
    public List<ProductNodeDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductNodeDTO(
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getYear(),
                        product.getDirector()
                ))
                .collect(Collectors.toList());
    }

    // Obtener producto por ID
    public ProductNodeDTO getProductById(Long id) {
        Optional<ProductNode> product = productRepository.findById(id);
        return product.map(productNode -> new ProductNodeDTO(
                productNode.getId(),
                productNode.getName(),
                productNode.getCategory(),
                productNode.getYear(),
                productNode.getDirector()
        )).orElse(null);
    }

    // Actualizar un producto
    public ProductNodeDTO updateProduct(Long id, ProductNodeDTO productDTO) {
        Optional<ProductNode> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            ProductNode product = existingProduct.get();
            product.setName(productDTO.getName());
            product.setCategory(productDTO.getCategory());
            product.setYear(productDTO.getYear());
            product.setDirector(productDTO.getDirector());

            ProductNode updatedProduct = productRepository.save(product);
            return new ProductNodeDTO(
                    updatedProduct.getId(),
                    updatedProduct.getName(),
                    updatedProduct.getCategory(),
                    updatedProduct.getYear(),
                    updatedProduct.getDirector()
            );
        }
        return null;
    }

    // Eliminar un producto
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}