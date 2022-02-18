package jvet;

/* Pet class for:
 * 
 *		JVet a veterinary management system in Java.
 *		
 *			Author: Nikos-Nikitas
 *
 *	****************************************************************
 *	*	Pets and their info are written to a file and accessed *
 *	*	when the user wants.				       *
 *	*						       	       *
 *	****************************************************************
 *	For more find me on GitHub: nikosnikitas
 * */
 

public class Pet {

		public int age = 0;

		public String name = "";
		
		//private boolean isHealthy = true;
		
		public String species = "";

		public char gender = 'M';
		
    public Pet(String name, String species,int age, char gender){

	this.name = name;
	this.species = species;
	this.age = age;
	this.gender = gender;
    	
    	}
	}
