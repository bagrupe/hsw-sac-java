package de.hsw.customer.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.hsw.customer.beans.Customer;
import de.hsw.customer.dtos.CustomerRequest;
import de.hsw.customer.dtos.CustomerResponse;
import de.hsw.customer.dtos.IbanRequest;
import de.hsw.customer.dtos.IbanResponse;
import de.hsw.customer.services.CustomerService;

@CrossOrigin
@RestController
public class CustomerController {
    
    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Powershell: invoke-restmethod -Method GET -Uri http://localhost:8081/api/customer/1
    @GetMapping(path = "/api/customer/{id}")
    public Optional<CustomerResponse> getCustomer(@PathVariable long id) {
        return CustomerResponse.ofCustomer(service.get(id));
    }

    public boolean validateIban(String iban) {
        WebClient client = WebClient.create("http://localhost:7071");
        IbanResponse response = client.get().uri("/api/ValidateIban?iban="+iban).retrieve().bodyToMono(IbanResponse.class).block();
        return response.isValid();
    }

    public IbanResponse createIban(IbanRequest request) {
        WebClient client = WebClient.create("http://localhost:7071");
        return client.post().uri("/api/CreateIban").bodyValue(request).retrieve().bodyToMono(IbanResponse.class).block();
    }

    // REST typischer HTTP 201 als 1. Alternative:
    // Powershell: invoke-restmethod -Method POST -Uri http://localhost:8081/api/customer -Body "{`"name`":`"Hans Wurst`", `"address`":`"Musterstr. 123, 12345 Musterstadt`", `"iban`":`"DE75888888880000012345`"}" -ContentType "application/json"
    // Powershell: invoke-restmethod -Method POST -Uri http://localhost:8081/api/customer -Body "{`"name`":`"Hans Wurst`", `"address`":`"Musterstr. 123, 12345 Musterstadt`", `"ibanRequest`":{`"countryCode`":`"DE`", `"accountNumber`":`"12345`", `"bankIdentification`":`"88888888`"}}" -ContentType "application/json"
    @PostMapping(path = "/api/customer")
    public ResponseEntity<?> create(@RequestBody CustomerRequest request) {
        System.out.println(request.toString());
        if(request.getIbanRequest() != null) {
            request.setIban(createIban(request.getIbanRequest()).getIban());
        }

        if(!validateIban(request.getIban())) {
            return ResponseEntity.badRequest().body("Ungültige IBAN");
        }

        Customer customer = service.save(request.toCustomer());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri(); 
        return ResponseEntity.created(location).build();
    }

    // Objekt zurückgeben als 2. Alternative
    // Powershell: invoke-restmethod -Method POST -Uri http://localhost:8081/api/customer2 -Body "{`"name`":`"Hans Wurst`", `"address`":`"Musterstr. 123, 12345 Musterstadt`", `"iban`":`"DE75888888880000012345`"}" -ContentType "application/json"
    // Powershell: invoke-restmethod -Method POST -Uri http://localhost:8081/api/customer2 -Body "{`"name`":`"Hans Wurst`", `"address`":`"Musterstr. 123, 12345 Musterstadt`", `"ibanRequest`":{`"countryCode`":`"DE`", `"accountNumber`":`"12345`", `"bankIdentification`":`"88888888`"}}" -ContentType "application/json"
    @PostMapping(path = "/api/customer2")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest request) {
        if(request.getIbanRequest() != null) {
            request.setIban(createIban(request.getIbanRequest()).getIban());
        }

        if(!validateIban(request.getIban())) {
            return ResponseEntity.badRequest().body("Ungültige IBAN");
        }

        Customer customer = service.save(request.toCustomer());
        return ResponseEntity.ok(new CustomerResponse(customer.getId(), customer.getName(), customer.getAddress(), customer.getIban()));
       
    }

    // Powershell: invoke-restmethod -Method GET -Uri http://localhost:8081/api/customers
    @GetMapping(path = "/api/customers")
    public List<CustomerResponse> getCustomers() {
        List<CustomerResponse> responses = new ArrayList<>();
        for(Customer c: service.getAllCustomers()) {
            responses.add(CustomerResponse.ofCustomer(Optional.of(c)).get());
        }
        return responses;
    }
}
