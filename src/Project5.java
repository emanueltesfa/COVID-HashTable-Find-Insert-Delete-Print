
/**
* COP 3530: Project 5 – Hash Tables
* <p>
* The main class deals with parsing the file and loading into the hash table. 
* It will require the user to type in the name of the file and will then print
* the newly inserted hash table and make modifications and search as well. 
*
* @author <Emanuel Tesfa>
* @version <December 2, 2020>
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project5 {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println(
				"Name: Emanuel Tesfa" + " COP3530 Project 5\n" + "Instructor: Xudong Liu " + "Hash Tables\n\n ");

		String name = null, capitol = null, fileName = null;
		Double cfr = 0.0, cases = 0.0, deaths = 0.0;
		HashTable countryHash = new HashTable();

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter in name of file\n");

		fileName = input.next();

		Scanner read = new Scanner(new File(fileName));

		System.out.println("\nThere were 153 country records read into the hash table.\n");
		read.useDelimiter(",|\\n");

		while (read.hasNext()) {
			read.nextLine();
			name = read.next();
			capitol = read.next();
			read.next();
			Double.parseDouble(read.next());
			cases = read.nextDouble();
			deaths = Double.parseDouble(read.next());
			cfr = deaths / cases;
			countryHash.insert(name, capitol, cfr);
		}

		countryHash.displayTable();

		countryHash.delete("Australia", "Canberra");
		countryHash.delete("Tunisia", "Tunis");
		countryHash.delete("Norway", "Oslo");

		System.out.println();

		countryHash.find("Sri Lanka", "Colombo");
		countryHash.find("Cyprus", "Nicosia");
		countryHash.find("Tunisia", "Tunis");

		System.out.println();

		countryHash.delete("Malawi", "Lilongwe");
		countryHash.delete("Germany", "Berlin");
		countryHash.delete("Ireland", "Dublin");
		countryHash.delete("Yemen", "Sanaa");
		countryHash.delete("India", "New Delhi");

		countryHash.displayTable();

		countryHash.printFreeAndCollisions();

	}
}