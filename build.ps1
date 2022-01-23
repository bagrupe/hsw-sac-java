$location = Get-Location

Set-Location "$PSScriptRoot/sac1-customer-spring"

& mvn clean install

Set-Location "$PSScriptRoot/sac1-iban-azjava"

& mvn clean install

Set-Location "$PSScriptRoot/sac1-iban-spring"

& mvn clean install

Set-Location $location