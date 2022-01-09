package de.hsw.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.hsw.customer.services.CustomerService;

@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    private CustomerService service;

    /**
     * Einfacher Test für erfolgreiche Erstellung
     */
    @Test
    public void createTest() {
        
    }

    


}
