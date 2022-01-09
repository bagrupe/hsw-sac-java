package de.hsw.customer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hsw.customer.beans.Customer;
import de.hsw.customer.repositories.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Ergebnis in der Datenbank speichern
     * @param request
     * @return
     */
    public Customer save(Customer request) {
        return repository.save(request);
    }

    public Optional<Customer> get(long id) {
        return repository.findById(id);
    }

    /**
     * Alle Customers ausgeben - als Beispiel für eine Auswertungsfunktion
     * @return
     */
    public List<Customer> getAllCustomers() {
        List<Customer> responses = new ArrayList<>();

        repository.findAll().forEach(r -> responses.add(r));

        return responses;
    }

}
