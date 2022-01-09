package de.hsw.iban.services;

import org.springframework.stereotype.Service;

import de.hsw.iban.utils.IbanUtil;

/**
 * Service-Kapsel um das IbanUtil
 */
@Service
public class IbanService {

    /**
     * IBAN prüfen
     * 
     * @param iban
     * @return
     */
    public boolean checkIban(String iban) {
        return IbanUtil.validate(iban);
    }

    /**
     * IBAN anhand der Komponenten erstellen, validieren und zurückgeben
     * 
     * @param countryCode Ländercode
     * @param accountNumber Kontonummer
     * @param bankIdentification BLZ
     * @return
     */
    public String createIban(String countryCode, String accountNumber, String bankIdentification) {
        return IbanUtil.create(countryCode, accountNumber, bankIdentification);
    }
}
