# FinalProject
This project is a reworked version of the Fitness Center program that was originally written in procedural style.

This version changes the code such that the code follows proper OOP principles. Enums, Classes, and Abstract Classes were created to make sure the program was less poorly written.

The issue that this program was completely created with the console solely in mind was not fixed, as all of the main methods use the console in some manner (And thus moving to a non-dependence would be a long and taxing re-write.)

However, if I would have seperated the program from a console dependence, I would have moved all of the functions that modify the data (ex: addTrainer, createClass, ect) into a new class that would be the dataHolder (probably called "fitnessCenter") and the console menuing and operations in another class called "consoleOperations," which would be called from the main function to start the console. That way the functionality of the program would have been completely independent of the medium used to run the program. (Specifically this would have been perfect to change to a visual program using Swing for example).