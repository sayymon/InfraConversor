@echo off

@REM ##############################################
@REM Mude apenas a linha abaixo
@REM ##############################################
set JAVA_CMD=-Xms512m -Xmx1g -XX:PermSize=512m -XX:MaxPermSize=512m -Djna.library.path=/dll/UHF-Reader.dll com.teste.main.Teste
 
@REM ##############################################
@REM aqui deve ser igual para os outros programas
@REM ##############################################
set APP_CP=.;

@REM Add all jars....
for %%i in ("lib\*.jar") do call "cpappend.bat" %%i

java -cp %APP_CP% %JAVA_CMD%
pause > out.txt
