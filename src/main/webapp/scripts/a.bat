@echo off
cd /d %1
activate tensorflow && python script1.py %2 %3 %4 %5
