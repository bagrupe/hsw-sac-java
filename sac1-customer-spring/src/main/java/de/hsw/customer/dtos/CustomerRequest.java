package de.hsw.customer.dtos;

import de.hsw.customer.beans.Customer;

public class CustomerRequest {
    private String name;

    private String address;

    private String iban;

    private IbanRequest ibanRequest;

    public CustomerRequest() {}

    public Customer toCustomer() {
        return new Customer(0, name, address, iban);
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

    public IbanRequest getIbanRequest() {
        return ibanRequest;
    }

    public void setIbanRequest(IbanRequest ibanRequest) {
        this.ibanRequest = ibanRequest;
    }

    @Override
    public String toString() {
        return "CustomerRequest [address=" + address + ", iban=" + iban + ", ibanRequest=" + ibanRequest + ", name="
                + name + "]";
    }
}
