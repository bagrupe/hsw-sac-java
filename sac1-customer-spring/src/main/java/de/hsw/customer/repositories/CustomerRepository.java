package de.hsw.customer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import de.hsw.customer.beans.Customer;

@Repository
@RepositoryRestResource(exported = false)
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
}
