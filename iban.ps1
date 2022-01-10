function Read-Response {
    [CmdletBinding()]
    param (
        $response,
        $headers,
        $status
    )
    
    return [PSCustomObject]@{
        Status = $status
        Iban = $response.iban
        Valid = $response.valid
        Message = $response.message
    }

}

$baseUrl = "http://localhost:7071/api/iban"
$validateUrl = "$($baseUrl)/ValidateIban"
$createUrl = "$($baseUrl)/CreateIban"

$response = invoke-restmethod -Method GET -Uri "$($validateUrl)?iban=DE75888888880000012345" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
Read-Response -response $response -status $status -headers $headers

$response = invoke-restmethod -Method GET -Uri "$($validateUrl)?iban=DEXX888888880000012345" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
Read-Response -response $response -status $status -headers $headers

$response = invoke-restmethod -Method POST -Uri $createUrl -Body "{`"countryCode`":`"DE`", `"accountNumber`":`"12345`", `"bankIdentification`":`"88888888`"}" -ContentType "application/json" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
Read-Response -response $response -status $status -headers $headers

$response = invoke-restmethod -Method POST -Uri $createUrl -Body "{`"countryCode`":`"XX`", `"accountNumber`":`"12345`", `"bankIdentification`":`"88888888`"}" -ContentType "application/json" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
Read-Response -response $response -status $status -headers $headers

$response = invoke-restmethod -Method GET -Uri "$($createUrl)?countryCode=DE&accountNumber=12345&bankIdentification=88888888" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
Read-Response -response $response -status $status -headers $headers

$response = invoke-restmethod -Method GET -Uri "$($createUrl)?countryCode=DE&accountNumber=12345&bankIdentification=X" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
Read-Response -response $response -status $status -headers $headers

$response = invoke-restmethod -Method POST -Uri $createUrl -Body "{}" -ContentType "application/json" -ResponseHeadersVariable "headers" -StatusCodeVariable "status" -SkipHttpErrorCheck
Read-Response -response $response -status $status -headers $headers