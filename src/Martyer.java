

import java.util.Date;

public class Martyer implements Comparable<Martyer> {
	private String name;
	private int age;
	private Date dateOfDeath;
	private char gender;
	private int numberOfAge;

	public Martyer(String name, int age, Date dateOfDeath, char gender) {
		this.name = name;
		this.age = age;
		this.dateOfDeath = dateOfDeath;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getNumberOfAge() {
		return numberOfAge;
	}

	public void setNumberOfAge(int numberOfAge) {
		this.numberOfAge = numberOfAge;
	}

	@Override
	public String toString() {
		return "Martyer name=" + name + ", age=" + age + ", dateOfDeath=" + dateOfDeath + ", gender=" + gender;
	}

	@Override
	public int compareTo(Martyer o) {
		if (this.getDateOfDeath().compareTo(o.getDateOfDeath()) >= 1)
			return 1;
		else if (this.getDateOfDeath().compareTo(o.getDateOfDeath()) < 1)
			return -1;

		return 0;
	}

}
