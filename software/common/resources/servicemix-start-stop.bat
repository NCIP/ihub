echo START/ STOP / RESTART SERVICEMIX USING A SCRIPT.


echo SMX Init Script

set operation=%1
set SERVICEMIX_HOME=%2

if "%operation%" == "start" (
echo Starting servicemix
%SERVICEMIX_HOME%/bin/servicemix
goto endIf1
)

if "%operation%" == "stop" (
echo STOP NOT AVAILABLE
goto endIf1
)

:endIf1
