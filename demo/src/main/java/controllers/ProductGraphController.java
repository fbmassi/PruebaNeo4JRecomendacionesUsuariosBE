package controllers;

import dtos.ProductNodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import entities.ProductNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ProductGraphService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductGraphController {

    @Autowired
    private ProductGraphService productGraphService;

    @PostMapping
    public ResponseEntity<ProductNode> createProduct(@RequestBody ProductNodeDTO productNodeDTO) {
        ProductNode createdProduct = productGraphService.createProduct(productNodeDTO);
        return ResponseEntity.created(URI.create("/api/products/" + createdProduct.getId())).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductNode>
    updateProduct(@PathVariable Long id, @RequestBody ProductNodeDTO productNodeDTO) {
        ProductNode updatedProduct = productGraphService.updateProduct(productNodeDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productGraphService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductNode>> findAllProducts() {
        List<ProductNode> products = productGraphService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductNode> findProductById(@PathVariable Long id) {
        ProductNode product = productGraphService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductNodeDTO>> findProductByName(@RequestParam String name) {
        List<ProductNodeDTO> products = productGraphService.findProductByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<ProductNode>> findHighlightedProducts() {
        List<ProductNode> products = productGraphService.findHighlightedProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/grouped")
    public ResponseEntity<List<ProductNode>> findProductsGroupedByCategory() {
        List<ProductNode> products = productGraphService.findProductsGroupedByCategory();
        return ResponseEntity.ok(products);
    }
}