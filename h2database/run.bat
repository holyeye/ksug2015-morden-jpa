@echo off
setlocal

call h2
start java -cp "%H2CP%" org.h2.tools.Server