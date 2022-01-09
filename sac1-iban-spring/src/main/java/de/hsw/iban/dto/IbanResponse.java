package de.hsw.iban.dto;

public class IbanResponse {
    private String iban;
    private boolean valid;
    private String message;

    public IbanResponse() {}

    public static IbanResponse ofSuccess(String iban) {
        return new IbanResponse(iban, true, "IBAN is valid");
    }

    public static IbanResponse ofFailure(String iban, String message) {
        return new IbanResponse(iban, false, message);
    }

    public IbanResponse(String iban, boolean valid, String message) {
        this.iban = iban;
        this.valid = valid;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "IbanResponse [iban=" + iban + ", message=" + message + ", valid=" + valid + "]";
    }

}
