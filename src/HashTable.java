import java.nio.charset.StandardCharsets;

/**
 * This class is where the insertion, deletion, find, hash and print methods are
 * made. These methods call methods inside the nested link list class since the
 * hash function uses separate chaining.
 *
 * @author <Emanuel Tesfa>
 * @version <December 3, 2020>
 */

class HashTable {

	private LinkToHash[] hashArray;
	private int arraySize = 307;

	/**
	 * The constructor just initializes the Array 
	 *
	 * @param
	 * @return
	 */

	public HashTable() {
		hashArray = new LinkToHash[arraySize];
		for (int j = 0; j < arraySize; j++)
			hashArray[j] = new LinkToHash();
	}

	/**
	 * 
	 * This method prints the array at the index and calls the other print method to
	 * print the nodes at the index.
	 *
	 * @param
	 * @return
	 */

	public void displayTable() {
		System.out.println();
		for (int j = 0; j < arraySize; j++) {
			System.out.print(j + ". \t\t");
			hashArray[j].displayList();
		}
		System.out.println();
	}

	/**
	 * THis method takes the parameters, changes them to their unicode values and
	 * combines the 2 for the key and then takes the remainder after divided 307
	 *
	 * @param String countryName, String capitol
	 * @return int key%arraySize
	 */

	public int hashFunc(String countryName, String capitol) {
		int key = 0;
		byte[] country = countryName.getBytes(StandardCharsets.US_ASCII);
		byte[] capitols = capitol.getBytes(StandardCharsets.US_ASCII);

		for (byte b : country)
			key += b;

		for (byte c : capitols)
			key += c;

		return (key % arraySize);
	}

	/**
	 * This method inserts the country capitol and cfr into the hash table
	 *
	 * @param String country, String capitol, double cfr
	 * @return
	 */

	public void insert(String country, String capitol, double cfr) {
		Node theLink = new Node(country, capitol, cfr);
		int hashVal = hashFunc(country, capitol);
		hashArray[hashVal].insert(theLink);
	}

	/**
	 * This methods deletes the country, capitol and CFR from the hashtable
	 *
	 * @param String country, String capitol
	 * @return
	 */

	public void delete(String country, String capitol) {
		int hashVal = hashFunc(country, capitol);
		hashArray[hashVal].delete(country);
	}

	/**
	 * This method searches through index's to see if the index of country exists
	 * and if so it returns the CFR else returns the value -1.
	 *
	 * @param String country, String capitol
	 * @return theLink.CFR if the link is not null
	 * @return -1 if the link is not found
	 */

	public double find(String country, String capitol) {
		int hashVal = hashFunc(country, capitol);
		Node theLink = hashArray[hashVal].find(country);

		if (theLink != null)
			return theLink.CFR;
		else
			return -1;
	}

	/**
	 * This method searches though each index and if more than 1 node exists
	 * (collision) then the counter is iterated once, and prints how many empty and
	 * collided cells their are.
	 *
	 * @param
	 * @return
	 */

	public void printFreeAndCollisions() {
		Node current;
		int collisionCount = 0, emptyCount = 0;

		for (int i = 0; i < arraySize; i++) {
			current = hashArray[i].first;
			if (current == null)
				emptyCount++;
			else if (current.nextNode != null)
				collisionCount++;
		}
		System.out.printf("There are %d empty and %d collison", emptyCount, collisionCount);

	}

	/**
	 * This method contains the link list implementation for the same methods as the
	 * hash function in order to create,delete and find links inside the array.
	 *
	 * @author <Emanuel Tesfa>
	 * @version <Decemeber 3, 2020>
	 */

	class LinkToHash {
		private Node first;
		private Node last;

		/**
		 * The constructor initializes first and last to null
		 *
		 * @param
		 * @return
		 */

		public LinkToHash() {
			first = null;
			last = null;
		}

		/**
		 * Checks to see if the first node is empty
		 *
		 * @param
		 * @return
		 */

		public boolean isEmpty() {
			return first == null;
		}

		/**
		 * This method checks to see if the node is empty and if it is, the loads in the
		 * link that is being passed in
		 * 
		 * @param Node newLink
		 * @return
		 */

		public void insert(Node newLink) {
			if (isEmpty())
				first = newLink;
			else
				last.nextNode = newLink;
			last = newLink;
		}

		/**
		 * If the node is found return the current node with a print statement rotating
		 * the CFR and name, else prints the name and that the country was not found
		 *
		 * @param String country
		 * @return Node current
		 * @return null
		 * 
		 */

		public Node find(String country) {
			Node current = first;
			while (current != null) {
				if (current.name.equals(country)) {
					System.out.println(country + " is found with CFR " + current.CFR);
					return current;
				}
				current = current.nextNode;
			}
			System.out.println(country + " was not found");
			return null;
		}

		/**
		 * This method deletes the node once found while leaving intact other nodes if
		 * they're apart of collided cells.
		 *
		 * @param String country
		 * @return
		 */

		public void delete(String country) {
			Node previous = null;
			Node current = first;

			while (current != null && !current.name.equals(country)) {
				previous = current;
				current = current.nextNode;
			}
			if (previous == null)
				first = first.nextNode;
			else
				previous.nextNode = current.nextNode;
			if (current != null)
				System.out.println(country + " has been deleted from hash table");
		}

		/**
		 * This method is being called from the previous print methods that will print
		 * the nodes at the cell and formats it
		 *
		 * @param
		 * @return
		 */

		public void displayList() {
			Node current = first;

			if (current != null) {
				while (current != null) {
					current.printNode();
					if (current.nextNode != null)
						System.out.print("\t\t");
					current = current.nextNode;

				}

			} else
				System.out.println("Empty");
		}

	}

	/**
	 * This method contains the constructor so that the local variables will be
	 * loaded and contains a print method.
	 *
	 * @author <Emanuel Tesfa >
	 * @version <Decemeber 3, 2020>
	 */

	private class Node {
		String name;
		String capitol;
		double CFR;
		Node nextNode;

		/**
		 * This constructor loads in the name, capitol and cfr.
		 *
		 * @param String name, String capitol, double CFR
		 * @return
		 */

		public Node(String name, String capitol, double CFR) {
			this.name = name;
			this.capitol = capitol;
			this.CFR = CFR;
		}

		/**
		 * This methods prints the nodes specifically.
		 *
		 * @param 
		 * @return 
		 */

		public void printNode() {
			System.out.printf("%-40s %-20s %-30.6f\n", name, capitol, CFR);
		}
	}

}
