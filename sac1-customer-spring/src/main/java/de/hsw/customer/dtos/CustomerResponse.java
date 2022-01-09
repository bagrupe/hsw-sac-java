package de.hsw.customer.dtos;

import java.util.Optional;

import de.hsw.customer.beans.Customer;

public class CustomerResponse {
    private long id;

    private String name;

    private String address;

    private String iban;

    public CustomerResponse() {}

    public CustomerResponse(long id, String name, String address, String iban) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.iban = iban;
    }

    public static Optional<CustomerResponse> ofCustomer(Optional<Customer> customer) {
        if(customer.isPresent()) {
            CustomerResponse r = new CustomerResponse(customer.get().getId(), customer.get().getName(), customer.get().getAddress(), customer.get().getIban());
            return Optional.of(r);
        }

        return Optional.empty();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    
}
