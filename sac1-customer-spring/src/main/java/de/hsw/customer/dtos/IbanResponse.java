package de.hsw.customer.dtos;

public class IbanResponse {
    private String iban;
    private boolean valid;

    public IbanResponse() {}

    public IbanResponse(String iban, boolean valid) {
        this.iban = iban;
        this.valid = valid;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


}
