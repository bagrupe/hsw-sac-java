$location = Get-Location

Set-Location "$PSScriptRoot/sac1-customer-spring"

& mvn clean

Set-Location "$PSScriptRoot/sac1-iban-azjava"

& mvn clean

Set-Location "$PSScriptRoot/sac1-iban-spring"

& mvn clean

Set-Location $location