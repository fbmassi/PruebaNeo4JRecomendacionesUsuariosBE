package io.reflectoring.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entities.CustomerNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.CustomerGraphRepository;
import services.CustomerGraphService;

@ExtendWith(MockitoExtension.class)
public class CustomerGraphServiceTest {

    @Mock
    private CustomerGraphRepository customerGraphRepository;

    @InjectMocks
    private CustomerGraphService customerService;

    private CustomerNode customerNode;

    @BeforeEach
    public void setUp() {
        customerNode = new CustomerNode();
        customerNode.setId(1L);
        customerNode.setFirstName("John");
        customerNode.setLastName("Doe");
    }

    @Test
    public void testCreateCustomer() {
        // Configurar el comportamiento simulado del repositorio
        when(customerGraphRepository.createCustomerNode(anyLong(), anyString(), anyString()))
                .thenReturn(customerNode);

        // Invocar el método de servicio
        CustomerNode createdCustomer = customerService.createCustomer(1L, "John", "Doe");

        // Verificar que el método del repositorio fue invocado correctamente
        verify(customerGraphRepository, times(1)).createCustomerNode(anyLong(), anyString(), anyString());

        // Verificar los resultados
        assertNotNull(createdCustomer);
        assertEquals(customerNode.getId(), createdCustomer.getId());
        assertEquals(customerNode.getFirstName(), createdCustomer.getFirstName());
    }

    @Test
    public void testUpdateCustomer() {
        // Configurar el comportamiento simulado del repositorio
        when(customerGraphRepository.updateCustomerNode(anyLong(), anyString(), anyString()))
                .thenReturn(customerNode);

        // Invocar el método de servicio
        CustomerNode updatedCustomer = customerService.updateCustomer(1L, "John", "Doe");

        // Verificar que el método del repositorio fue invocado correctamente
        verify(customerGraphRepository, times(1)).updateCustomerNode(anyLong(), anyString(), anyString());

        // Verificar los resultados
        assertNotNull(updatedCustomer);
        assertEquals(customerNode.getId(), updatedCustomer.getId());
        assertEquals(customerNode.getFirstName(), updatedCustomer.getFirstName());
    }

    @Test
    public void testDeleteCustomer() {
        // Invocar el método de servicio
        customerService.deleteCustomer(1L, "John", "Doe");

        // Verificar que el método del repositorio fue invocado correctamente
        verify(customerGraphRepository, times(1)).deleteCustomerNode(1L, "John", "Doe");
    }
}
