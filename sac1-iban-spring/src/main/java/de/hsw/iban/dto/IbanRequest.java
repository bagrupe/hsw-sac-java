package de.hsw.iban.dto;

public class IbanRequest {
    private String countryCode; 
    private String accountNumber;
    private String bankIdentification;
    
    public IbanRequest() {
    }

    public IbanRequest(String countryCode, String accountNumber, String bankIdentification) {
        this.countryCode = countryCode;
        this.accountNumber = accountNumber;
        this.bankIdentification = bankIdentification;
    }

    public String getCountryCode() {
        return countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankIdentification() {
        return bankIdentification;
    }

    public void setBankIdentification(String bankIdentification) {
        this.bankIdentification = bankIdentification;
    }

    @Override
    public String toString() {
        return "IbanRequest [accountNumber=" + accountNumber + ", bankIdentification=" + bankIdentification
                + ", countryCode=" + countryCode + "]";
    }
}
