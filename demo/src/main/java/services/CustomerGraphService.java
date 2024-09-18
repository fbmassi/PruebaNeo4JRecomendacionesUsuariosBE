package services;

import org.springframework.stereotype.Service;
import repositories.CustomerGraphRepository;

@Service
public class CustomerGraphService {

    private final CustomerGraphRepository customerRepository;

    public CustomerGraphService(CustomerGraphRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

}