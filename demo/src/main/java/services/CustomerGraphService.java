package services;

import entities.CustomerNode;
import entities.ProductNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CustomerGraphRepository;
import repositories.ProductGraphRepository;
import java.util.List;

@Service
public class CustomerGraphService {

    @Autowired
    private CustomerGraphRepository customerGraphRepository;

    @Autowired
    private ProductGraphRepository productGraphRepository;

    // Crear un nuevo cliente
    public CustomerNode createCustomer(Long id, String firstName, String lastName) {
        return customerGraphRepository.createCustomerNode(id, firstName, lastName);
    }

    // Actualizar un cliente
    public CustomerNode updateCustomer(Long id, String firstName, String lastName) {
        return customerGraphRepository.updateCustomerNode(id, firstName, lastName);
    }

    // Borrar a un clente
    public void deleteCustomer(Long id, String firstName, String lastName) {
        customerGraphRepository.deleteCustomerNode(id, firstName, lastName);
    }

    // Encontrar un cliente por su ID
    public CustomerNode findCustomerById(Long id) {
        return customerGraphRepository.findCustomerNodeById(id);
    }

    // Encontrar todos los clientes
    public List<CustomerNode> findAllCustomers() {
        return customerGraphRepository.findAllCustomerNodes();
    }

    // Agregar un producto a los recientemente vistos
    public void addProductToRecentlyViewed(Long customerId, Long productId) {
        customerGraphRepository.addProductToRecentlyViewed(customerId, productId);
    }

    // Obtener productos recientemente vistos por un cliente
    public List<ProductNode> findRecentlyViewedProducts(Long customerId) {
        return customerGraphRepository.findRecentlyViewedProductsByCustomerNode(customerId);
    }

    // Agregar un producto a favoritos
    public void addProductToFavorites(Long customerId, Long productId) {
        customerGraphRepository.addProductToFavorites(customerId, productId);
    }

    // Obtener productos favoritos de un cliente
    public List<ProductNode> findFavoriteProducts(Long customerId) {
        return customerGraphRepository.findFavoriteProducts(customerId);
    }

    // Eliminar un producto de favoritos
    public void deleteFavoriteProduct(Long customerId, Long productId) {
        customerGraphRepository.deleteFavoriteProduct(customerId, productId);
    }

    // Recomendaciones por categoría
    public List<ProductNode> recommendByCategory(Long customerId) {
        return customerGraphRepository.recommendProductsByCategory(customerId);
    }

    // Recomendaciones por década de lanzamiento
    public List<ProductNode> recommendByDecade(Long customerId) {
        return customerGraphRepository.recommendProductsByDecade(customerId);
    }

    // Recomendaciones por director
    public List<ProductNode> recommendByDirector(Long customerId) {
        return customerGraphRepository.recommendProductsByDirector(customerId);
    }
}