package edu.lewis.fitness_center;

public class Member extends Person
{
	private MembershipType membershipType;
	private boolean isActive;
	private double balance;
	
	public Member(int id, String name, MembershipType membershipType, boolean isActive, double balance)
	{
		super(id, name);
		this.membershipType = membershipType;
		this.isActive = isActive;
		this.balance = balance;
	}
	
	
	//Getters:

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public boolean isActive() {
		return isActive;
	}

	public double getBalance() {
		return balance;
	}
	
	
	//Setters:
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
