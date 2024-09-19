package services;

import dtos.ProductNodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProductGraphRepository;
import entities.ProductNode;

import java.util.List;

@Service
public class ProductGraphService {

    @Autowired
    private ProductGraphRepository productGraphRepository;

    // Crear un nuevo producto
    public ProductNode createProduct(ProductNodeDTO productNodeDTO) {
        return productGraphRepository.createProductNode(
                productNodeDTO.getId(),
                productNodeDTO.getName(),
                productNodeDTO.getCategory(),
                productNodeDTO.getYear(),
                productNodeDTO.getDirector()
        );
    }

    // Actualizar un producto
    public ProductNode updateProduct(ProductNodeDTO productNodeDTO) {
        return productGraphRepository.updateProductNode(
                productNodeDTO.getId(),
                productNodeDTO.getName(),
                productNodeDTO.getCategory(),
                productNodeDTO.getYear(),
                productNodeDTO.getDirector()
        );
    }

    // Eliminar un producto
    public void deleteProduct(Long id) {
        productGraphRepository.deleteProductNode(id);
    }

    // Buscar todos los productos
    public List<ProductNode> findAllProducts() {
        return productGraphRepository.findAllProductNodes();
    }

    // Buscar producto por id
    public ProductNode findProductById(Long id) {
        return productGraphRepository.findProductNodeById(id);
    }

    // Buscar producto por nombre
    public ProductNode findProductByName(String name) {
        return productGraphRepository.findProductNodeByName(name);
    }

    // Buscar productos destacados
    public List<ProductNode> findHighlightedProducts() {
        return productGraphRepository.findHighlightedProductNodes();
    }

    // Agrupación de productos por categoría
    public List<ProductNode> findProductsGroupedByCategory() {
        return productGraphRepository.findProductNodesGroupedByCategory();
    }

}