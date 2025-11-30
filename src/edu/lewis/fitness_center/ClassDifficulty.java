package edu.lewis.fitness_center;

public enum ClassDifficulty 
{
	BEGINNER("beginner"),
	INTERMEDIATE("intermediate"),
	ADVANCED("advanced");
	
	public String name;
	
	ClassDifficulty(String name)
	{
		this.name = name;
	}
}
