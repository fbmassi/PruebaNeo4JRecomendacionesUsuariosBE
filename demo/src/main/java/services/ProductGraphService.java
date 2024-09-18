package services;

import org.springframework.stereotype.Service;
import repositories.CustomerGraphRepository;
import repositories.ProductGraphRepository;

import java.util.stream.Collectors;

@Service
public class ProductGraphService {
    private final ProductGraphRepository productRepository;
    private final CustomerGraphRepository customerRepository;

    public ProductGraphService(ProductGraphRepository productRepository, CustomerGraphRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

}