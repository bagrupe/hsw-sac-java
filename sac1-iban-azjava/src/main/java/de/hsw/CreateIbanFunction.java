package de.hsw;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import de.hsw.dto.IbanRequest;
import de.hsw.dto.IbanResponse;

import java.util.Optional;

public class CreateIbanFunction {

    /**
     * Beispiel: 
     * Option A: GET Parameter
     * invoke-restmethod -Method GET -Uri "http://localhost:7071/api/CreateIban?countryCode=DE&accountNumber=12345678&bankIdentification=11111111"
     * Option B: POST Anfrage (Powershell Beispiel)
     * invoke-restmethod -Method POST -Uri http://localhost:7071/api/CreateIban -Body "{`"countryCode`":`"DE`", `"accountNumber`":`"12345`", `"bankIdentification`":`"88888888`"}" -ContentType "application/json"
     */
    @FunctionName("CreateIban")
    public HttpResponseMessage createIban(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<IbanRequest>> request,
            final ExecutionContext context) {
        

        String countryCode = null, accountNumber = null, bankIdentification = null;

        if(request.getHttpMethod() == HttpMethod.GET) {
            countryCode = request.getQueryParameters().get("countryCode");
            accountNumber = request.getQueryParameters().get("accountNumber");
            bankIdentification = request.getQueryParameters().get("bankIdentification");
        } else {
            Optional<IbanRequest> body = request.getBody();
            if(body.isPresent()) {
                countryCode = body.get().getCountryCode();
                accountNumber = body.get().getAccountNumber();
                bankIdentification = body.get().getBankIdentification();
            }
        }
        context.getLogger().info("CreateIban " + request.getHttpMethod() + ": " + countryCode + "/" + bankIdentification + "/" + accountNumber);
        
        try {
            String iban = IbanUtil.create(countryCode, accountNumber, bankIdentification);
            return request.createResponseBuilder(HttpStatus.OK).body(IbanResponse.ofSuccess(iban)).build();
        } catch(IllegalArgumentException e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(IbanResponse.ofFailure("<invalid>", e.getMessage())).build();
        }
    }
}
