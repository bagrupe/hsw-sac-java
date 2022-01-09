package de.hsw;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import de.hsw.dto.IbanResponse;

import java.util.Optional;

public class ValidateIbanFunction {

    /**
     * Beispiele: http://localhost:7071/api/ValidateIban?iban=DE15111111110000000001
     * http://localhost:7071/api/ValidateIban?iban=DE1234
     */
    @FunctionName("ValidateIban")
    public HttpResponseMessage validateIban(@HttpTrigger(name = "req", methods = {
            HttpMethod.GET }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        try {
            // Parse query parameter
            final String iban = request.getQueryParameters().get("iban");
            if (IbanUtil.validate(iban)) {
                return request.createResponseBuilder(HttpStatus.OK).body(IbanResponse.ofSuccess(iban)).build();
            } else {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                        .body(IbanResponse.ofFailure(iban, "IBAN konnte nicht geparst werden")).build();
            }
        } catch (Exception e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body(IbanResponse.ofFailure("<invalid>", e.getMessage())).build();
        }

    }
}
