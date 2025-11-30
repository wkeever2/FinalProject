package edu.lewis.fitness_center;

public enum MembershipType 
{
	STUDENT("student", 0.5),
	FACULTY("faculty", 0.75),
	COMMUNITY("community", 1);
	
	public String name;
	public double discountMultiplier; //Easier to put here, than to have the main code determine this for itself
	
	MembershipType(String name, double discountMultiplier)
	{
		this.name = name;
		this.discountMultiplier = discountMultiplier;
	}
}
