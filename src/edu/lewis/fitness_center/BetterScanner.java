package edu.lewis.fitness_center;

import java.util.Scanner;

public class BetterScanner 
{
	//Vars:
    private Scanner scanner = new Scanner(System.in);
    
    //No constructor needed!
    
    
    //Methods:
    public int readInt(String prompt, Integer minValue, Integer maxValue) 
    {
        while (true) 
        {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try 
            {
                int value = Integer.parseInt(line);
                if (minValue != null && value < minValue) 
                {
                    System.out.println("Please enter a value >= " + minValue);
                    continue;
                }
                if (maxValue != null && value > maxValue) 
                {
                    System.out.println("Please enter a value <= " + maxValue);
                    continue;
                }
                return value;
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public String readNonempty(String prompt) 
    {
        while (true) 
        {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) 
            {
                return value;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    public void pause() 
    {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    public String nextLine()
    {
    	return scanner.nextLine();
    }
}
