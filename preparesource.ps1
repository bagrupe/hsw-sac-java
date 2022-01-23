$location = Get-Location

. $PSScriptRoot/clean.ps1

$classpaths = Get-ChildItem -Path . -Include .classpath -Recurse -Hidden
foreach($file in $classpaths) {
    Remove-Item $file -Force
}
$projects = Get-ChildItem -Path . -Include .project -Recurse -Hidden
foreach($file in $projects) {
    Remove-Item $file -Force
}
$settings = Get-ChildItem -Path . -Include .settings -Recurse -Hidden
foreach($file in $settings) {
    Remove-Item $file -Recurse -Force
}

Set-Location $PSScriptRoot
Remove-Item $PSScriptRoot/../hsw-sac-java.zip
& zip -r $PSScriptRoot/../hsw-sac-java.zip . -x '*.git*' -x '*.DS_Store*'

Set-Location $location