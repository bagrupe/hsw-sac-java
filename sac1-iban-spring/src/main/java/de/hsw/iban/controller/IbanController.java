package de.hsw.iban.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hsw.iban.dto.IbanRequest;
import de.hsw.iban.dto.IbanResponse;
import de.hsw.iban.services.IbanService;

@CrossOrigin
@RestController
public class IbanController {
    private static final Logger log = Logger.getLogger(IbanController.class.getName());
    
    private IbanService service;

    @Autowired
    public IbanController(IbanService service) {
        this.service = service;
    }

    // Powershell: invoke-restmethod -Method GET -Uri "http://localhost:7071/api/ValidateIban?iban=DE75888888880000012345"
    @GetMapping(path = "/api/ValidateIban")
    public ResponseEntity<IbanResponse> validateIban(@RequestParam String iban) {
        log.log(Level.INFO, "ValidateIban Request: " + iban);
        ResponseEntity<IbanResponse> responseEntity;

        try {
            if(service.checkIban(iban)) {
                responseEntity = ResponseEntity.ok(IbanResponse.ofSuccess(iban));
            } else {
                responseEntity = ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(IbanResponse.ofFailure(iban, "IBAN konnte nicht geparst werden"));
            }
        } catch(Exception e) {
            responseEntity = ResponseEntity.badRequest().body(IbanResponse.ofFailure(iban, e.getMessage()));
        }

        log.log(Level.INFO, "ValidateIban Response: " + responseEntity.getBody());
        return responseEntity;
    }

    // Powershell: invoke-restmethod -Method POST -Uri "http://localhost:7071/api/CreateIban" -Body "{`"countryCode`":`"DE`", `"accountNumber`":`"12345`", `"bankIdentification`":`"88888888`"}" -ContentType "application/json"
    // @RequestBody wird im JSON Format erwartet und nicht x-www-form-urlencoded
    @PostMapping(path = "/api/CreateIban")
    public ResponseEntity<IbanResponse> createIban(@RequestBody IbanRequest request) {
        log.log(Level.INFO, "CreateIban POST: " + request);
        ResponseEntity<IbanResponse> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(IbanResponse.ofSuccess(service.createIban(request.getCountryCode(), request.getAccountNumber(), request.getBankIdentification())));
        } catch(Exception e) {
            responseEntity = ResponseEntity.badRequest().body(IbanResponse.ofFailure("<invalid>", e.getMessage()));
        }

        log.log(Level.INFO, "CreateIban Response: " + responseEntity.getBody());
        return responseEntity;
    }

    // Powershell: invoke-restmethod -Method GET -Uri "http://localhost:7071/api/CreateIban?countryCode=DE&accountNumber=12345&bankIdentification=88888888"
   @GetMapping(path = "/api/CreateIban")
    public ResponseEntity<IbanResponse> createIbanGet(@RequestParam String countryCode, @RequestParam String accountNumber, @RequestParam String bankIdentification) {
        log.log(Level.INFO, "CreateIban GET: " + countryCode + "/"+bankIdentification+"/"+accountNumber);
        ResponseEntity<IbanResponse> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(IbanResponse.ofSuccess(service.createIban(countryCode, accountNumber, bankIdentification)));
        } catch(Exception e) {
            responseEntity = ResponseEntity.badRequest().body(IbanResponse.ofFailure("<invalid>", e.getMessage()));
        }

        log.log(Level.INFO, "CreateIban Response: " + responseEntity.getBody());
        return responseEntity;
    }
}
