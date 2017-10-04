@echo off
javac *.java
pause

for %%f in (*.java) do java Convert %%f
for %%o in (*.t) do for %%f in (*.t) do if not %%o==%%f java Detect %%o %%f
pause

