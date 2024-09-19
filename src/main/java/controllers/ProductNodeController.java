package controllers;

import dtos.ProductNodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ProductNodeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductNodeController {

    @Autowired
    private ProductNodeService productService;

    // Crear un producto
    @PostMapping("/new_product")
    public ResponseEntity<ProductNodeDTO> createProduct(@RequestBody ProductNodeDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    // Obtener todos los productos
    @GetMapping(("/products"))
    public ResponseEntity<List<ProductNodeDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Obtener producto por ID
    @GetMapping("/get_product/{id}")
    public ResponseEntity<ProductNodeDTO> getProductById(@PathVariable Long id) {
        ProductNodeDTO productDTO = productService.getProductById(id);
        if (productDTO != null) {
            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Actualizar producto
    @PutMapping("/update_product/{id}")
    public ResponseEntity<ProductNodeDTO> updateProduct(@PathVariable Long id, @RequestBody ProductNodeDTO productDTO) {
        ProductNodeDTO updatedProduct = productService.updateProduct(id, productDTO);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar producto
    @DeleteMapping("/delete_product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}