package edu.lewis.fitness_center;

import java.util.List;

public class Trainer extends Person
{
	private String specialty;
	private List<String> schedule;
	
	public Trainer(int id, String name, String specialty, List<String> schedule)
	{
		super(id, name);
		this.specialty = specialty;
		this.schedule = schedule;
	}

	
	//Getters:=
	public String getSpecialty() {
		return specialty;
	}

	public List<String> getSchedule() {
		return schedule;
	}
}
