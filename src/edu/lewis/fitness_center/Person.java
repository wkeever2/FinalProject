package edu.lewis.fitness_center;

public abstract class Person 
{
	private int id;
	private String name;

	protected Person(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	//Getters:
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
