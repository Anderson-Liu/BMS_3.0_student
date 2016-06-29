@echo off
set CURRENT_DIR=%cd%
cd %CURRENT_DIR%
call java -Xbootclasspath/a: bamclient.BAMClient
@pause