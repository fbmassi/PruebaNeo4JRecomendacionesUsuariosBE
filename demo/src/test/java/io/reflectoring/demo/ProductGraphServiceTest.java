package io.reflectoring.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dtos.ProductNodeDTO;
import entities.ProductNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.ProductGraphRepository;
import services.ProductGraphService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductGraphServiceTest {

    @Mock
    private ProductGraphRepository productGraphRepository;

    @InjectMocks
    private ProductGraphService productService;

    private ProductNode productNode;
    private ProductNodeDTO productNodeDTO;

    @BeforeEach
    public void setUp() {
        productNode = new ProductNode();
        productNode.setId(1L);
        productNode.setName("Inception");
        productNode.setCategory("Science Fiction");
        productNode.setYear(2010);
        productNode.setDirector("Christopher Nolan");

        productNodeDTO = new ProductNodeDTO(1L, "Inception", "Science Fiction", 2010, "Christopher Nolan");
    }

    @Test
    public void testCreateProduct() {
        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode createdProduct = productService.createProduct(productNodeDTO);

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString());

        // Verificar los resultados
        assertNotNull(createdProduct);
        assertEquals(productNode.getId(), createdProduct.getId());
        assertEquals(productNode.getName(), createdProduct.getName());
    }

    @Test
    public void testUpdateProduct() {

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode createdProduct = productService.createProduct(productNodeDTO);

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.updateProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode updatedProduct = productService.updateProduct(productNodeDTO);

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).updateProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString());

        // Verificar los resultados
        assertNotNull(updatedProduct);
        assertEquals(productNode.getId(), updatedProduct.getId());
        assertEquals(productNode.getName(), updatedProduct.getName());
    }

    @Test
    public void testDeleteProduct() {

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode createdProduct = productService.createProduct(productNodeDTO);

        // Invocar el método de servicio
        productService.deleteProduct(1L);

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).deleteProductNode(1L);
    }

    @Test
    public void testFindAllProducts() {

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode createdProduct = productService.createProduct(productNodeDTO);

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.findAllProductNodes()).thenReturn(Arrays.asList(productNode));

        // Invocar el método de servicio
        List<ProductNode> products = productService.findAllProducts();

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).findAllProductNodes();

        // Verificar los resultados
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(productNode.getId(), products.get(0).getId());
    }

    @Test
    public void testFindProductById() {

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode createdProduct = productService.createProduct(productNodeDTO);

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.findProductNodeById(1L)).thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode foundProduct = productService.findProductById(1L);

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).findProductNodeById(1L);

        // Verificar los resultados
        assertNotNull(foundProduct);
        assertEquals(productNode.getId(), foundProduct.getId());
    }

    @Test
    public void testFindProductByName() {
        // Crear objetos de prueba
        ProductNode productNode2 = new ProductNode();
        productNode2.setId(3L);
        productNode2.setName("Inception 2");
        productNode2.setCategory("Action");
        productNode2.setYear(2014);
        productNode2.setDirector("Christopher Nolan");

        List<ProductNode> productNodeList = Arrays.asList(productNode, productNode2);

        // Crear los DTOs esperados
        ProductNodeDTO productNodeDTO1 = new ProductNodeDTO(1L, "Inception", "Sci-Fi", 2010, "Christopher Nolan");
        ProductNodeDTO productNodeDTO2 = new ProductNodeDTO(3L, "Inception 2", "Action", 2014, "Christopher Nolan");

        // Configurar el comportamiento simulado del repositorio para devolver una lista de nodos
        when(productGraphRepository.findProductNodesByName("Incept")).thenReturn(productNodeList);

        // Invocar el método del servicio
        List<ProductNodeDTO> foundProducts = productService.findProductByName("Incept");

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).findProductNodesByName("Incept");

        // Verificar los resultados
        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());

        // Verificar que los DTOs son correctos
        assertEquals(productNodeDTO1.getId(), foundProducts.get(0).getId());
        assertEquals(productNodeDTO1.getName(), foundProducts.get(0).getName());

        assertEquals(productNodeDTO2.getId(), foundProducts.get(1).getId());
        assertEquals(productNodeDTO2.getCategory(), foundProducts.get(1).getCategory());
    }


    @Test
    public void testFindHighlightedProducts() {

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode createdProduct = productService.createProduct(productNodeDTO);

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.findHighlightedProductNodes()).thenReturn(Arrays.asList(productNode));

        // Invocar el método de servicio
        List<ProductNode> highlightedProducts = productService.findHighlightedProducts();

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).findHighlightedProductNodes();

        // Verificar los resultados
        assertNotNull(highlightedProducts);
        assertEquals(1, highlightedProducts.size());
        assertEquals(productNode.getId(), highlightedProducts.get(0).getId());
    }

    @Test
    public void testFindProductsGroupedByCategory() {

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.createProductNode(anyLong(), anyString(), anyString(), anyInt(), anyString()))
                .thenReturn(productNode);

        // Invocar el método de servicio
        ProductNode createdProduct = productService.createProduct(productNodeDTO);

        // Configurar el comportamiento simulado del repositorio
        when(productGraphRepository.findProductNodesGroupedByCategory()).thenReturn(Arrays.asList(productNode));

        // Invocar el método de servicio
        List<ProductNode> groupedProducts = productService.findProductsGroupedByCategory();

        // Verificar que el método del repositorio fue invocado correctamente
        verify(productGraphRepository, times(1)).findProductNodesGroupedByCategory();

        // Verificar los resultados
        assertNotNull(groupedProducts);
        assertEquals(1, groupedProducts.size());
        assertEquals(productNode.getId(), groupedProducts.get(0).getId());
    }
}
