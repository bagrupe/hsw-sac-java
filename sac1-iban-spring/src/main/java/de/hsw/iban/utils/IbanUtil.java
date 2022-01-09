package de.hsw.iban.utils;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utilityklasse für Validierung und Zusammensetzen von IBANs
 */
public class IbanUtil {
    private static final Logger log = Logger.getLogger(IbanUtil.class.getName());

    /**
     * IBAN aus Ländercode, Kontonummer und BLZ erstellen und gleichzeitig
     * validieren
     * 
     * @param countryCode
     * @param accountNumber
     * @param bankIdentification
     * @return
     */
    public static String create(String countryCode, String accountNumber, String bankIdentification) {
        log.log(Level.INFO, String.format("Country: %1$s BankID: %2$8s Account: %3$10s", countryCode,
                bankIdentification, accountNumber));
        if (countryCode == null || accountNumber == null || bankIdentification == null) {
            throw new IllegalArgumentException("Parameter dürfen nicht null sein");
        }

        if (!countryCode.equals("DE")) {
            throw new IllegalArgumentException("Derzeit nur für DE implementiert");
        }

        if (bankIdentification.startsWith("0") || bankIdentification.startsWith("9")) {
            throw new IllegalArgumentException("Bankleitzahl darf nicht mit 0 der 9 beginnen");
        }

        if (bankIdentification.length() != 8) {
            throw new IllegalArgumentException("Bankleitzahl muss 8 Ziffern lang sein");
        }

        if (accountNumber.length() > 10) {
            throw new IllegalArgumentException("Kontonummer darf maximal 10-stellig sein");
        }

        if (accountNumber.replace("0", "").isEmpty()) {
            throw new IllegalArgumentException("Kontonummer muss gefüllt sein");
        }

        String checkIban = String.format("%1$s%2$10s%3$d", bankIdentification, accountNumber, 131400).replace(" ", "0");
        long checkDigit = new BigInteger(checkIban).remainder(new BigInteger("97")).longValue();

        String iban = String.format("%1$s%2$02d%3$s%4$10s", countryCode, checkDigit, bankIdentification, accountNumber)
                .replace(" ", "0");
        log.log(Level.INFO, String.format("Country: %1$s BankID: %2$8s Account: %3$10s => IBAN: %4$s", countryCode,
                bankIdentification, accountNumber, iban));
        return iban;
    }

    /**
     * IBAN validieren
     * 
     * @param iban vollständige IBAN
     * @return true wenn die IBAN valide ist (Prüfziffer stimmt, Komponenten sind valide)
     */
    public static boolean validate(String iban) {
        log.log(Level.INFO, "Prüfe IBAN: " + iban);
        try {
            String countryCode = iban.substring(0, 2);
            String bankIdentification = iban.substring(4, 12);
            String accountNumber = iban.substring(12, 22);
            
            return iban.equals(create(countryCode, accountNumber, bankIdentification));
        }
        catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }

}
