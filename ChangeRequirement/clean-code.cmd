:: Date   : 2021/06/02
:: Author : KevinZonda
:: Descrip: Clean bluej project's useless file

@echo off
cd src
del /q "*.class"
del /q "*.ctxt"
del /q "~*"
del /q "*.txt"

:: rebuild bluej file
del /q "package.bluej"
type nul > "package.bluej"
xcopy assets src /e /Y /d