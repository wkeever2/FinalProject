package edu.lewis.fitness_center;

import java.util.ArrayList;
import java.util.List;

public class FitnessClass 
{
	private int id;
	private String name;
	private ClassDifficulty difficulty;
	private int capacity;
	private List<Integer> enrolledIds;
	
	public FitnessClass(int id, String name, ClassDifficulty difficulty, int capacity)
	{
		this.id = id;
		this.name = name;
		this.difficulty = difficulty;
		this.capacity = capacity;
		enrolledIds = new ArrayList<Integer>();
	}
	
	
	//Getters:
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ClassDifficulty getDifficulty() {
		return difficulty;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Integer> getEnrolledIds() {
		return enrolledIds;
	}
}
