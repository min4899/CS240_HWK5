import java.util.Iterator;

public class Demo
{
  public static void main(String[] args)
  {
    
    System.out.println("TESTING SORTED ARRAY DICTIONARY");
    SortedArrayDictionary dictionary = new SortedArrayDictionary(10);
    System.out.println("Created a new dictionary for SortedArrayDictionary");
    System.out.println();

    /*
    System.out.println("TESTING SORTED LINKED DICTIONARY");
    SortedLinkedDictionary dictionary = new SortedLinkedDictionary();
    System.out.println("Created a new dictionary for SortedLinkedDictionary");
    System.out.println();
    */
    System.out.println("Adding entries");
    dictionary.add("Morgan", "Cypress College");
    dictionary.add("Brian", "Cypress College");
    dictionary.add("Gino", "UC Berkeley");
    dictionary.add("Patrick", "Cypress College");
    dictionary.add("Shaan", "Humboldt University");
    dictionary.add("Nick", "UC San Diego");
    dictionary.add("Daniel", "UC Riverside");
    dictionary.add("Min", "Cal Poly Pomona");
    System.out.println();

    System.out.println("The list contains " + dictionary.getSize() +
                       " items, as follows: ");
    Iterator<String> keyIterator = dictionary.getKeyIterator();
    Iterator<String> valueIterator = dictionary.getValueIterator();
    while(keyIterator.hasNext())
    {
      System.out.println(keyIterator.next() + ", "  + valueIterator.next());
    }
    System.out.println();

    System.out.println("Adding more than 10 (testing for SortedArray version), adding 4 more: Dalee, Jerry, Varun, Rohan.");
    dictionary.add("Dalee", "UC Los Angeles");
    dictionary.add("Jerry", "UC Riverside");
    dictionary.add("Varun", "Cal Poly Pomona");
    dictionary.add("Rohan", "Cal Poly Pomona");
    System.out.println();

    System.out.println("The list contains " + dictionary.getSize() +
                       " items, as follows: ");
    Iterator<String> keyIterator2 = dictionary.getKeyIterator();
    Iterator<String> valueIterator2 = dictionary.getValueIterator();
    while(keyIterator2.hasNext())
    {
      System.out.println(keyIterator2.next() + ", "  + valueIterator2.next());
    }
    System.out.println();

    System.out.println("Morgan and Patrick is transfering");
    System.out.println("Morgan is transferring from: " + dictionary.add("Morgan", "UC Irvine"));
    System.out.println("Patrick is transferring from: " + dictionary.add("Patrick", "UC Davis"));
    System.out.println("Where does Morgan go now?: " + dictionary.getValue("Morgan"));
    System.out.println("Where does Patrick go now?: " + dictionary.getValue("Patrick"));
    System.out.println();

    System.out.println("Removing people from dictionary");
    System.out.println("Removing Brian, was from: " + dictionary.remove("Brian"));
    System.out.println("Removing Dalee, was from: " + dictionary.remove("Dalee"));
    System.out.println("Removing Shaan, was from: " + dictionary.remove("Shaan"));
    System.out.println();

    System.out.println("Checking people status");
    System.out.println("Is Brian in the list?: " + dictionary.contains("Brian"));
    System.out.println("Is Dalee in the list?: " + dictionary.contains("Dalee"));
    System.out.println("Is Shaan in the list?: " + dictionary.contains("Shaan"));
    System.out.println("Is Min in the list?: " + dictionary.contains("Min"));
    System.out.println("Is Morgan in the list?: " + dictionary.contains("Morgan"));
    System.out.println("Is Gino in the list?: " + dictionary.contains("Gino"));
    System.out.println();

    System.out.println("The list contains " + dictionary.getSize() +
                       " items, as follows: ");
    Iterator<String> keyIterator3 = dictionary.getKeyIterator();
    Iterator<String> valueIterator3 = dictionary.getValueIterator();
    while(keyIterator3.hasNext())
    {
      System.out.println(keyIterator3.next() + ", "  + valueIterator3.next());
    }
    System.out.println();

    System.out.println("Clearing dictionary");
    dictionary.clear();
    System.out.println("Is the dictionary empty?: " + dictionary.isEmpty());
    System.out.println();

    System.out.println("The list contains " + dictionary.getSize() +
                       " items, as follows: ");
    Iterator<String> keyIterator4 = dictionary.getKeyIterator();
    Iterator<String> valueIterator4 = dictionary.getValueIterator();
    while(keyIterator4.hasNext())
    {
      System.out.println(keyIterator4.next() + ", "  + valueIterator4.next());
    }
    System.out.println();

    System.out.println("Adding new entries");
    dictionary.add("Ferdie", "UC Berkeley");
    dictionary.add("Kevin", "Rice University");
    dictionary.add("Danny", "UC Santa Barbara");
    dictionary.add("Edward", "Cypress College");
    System.out.println();

    System.out.println("Attempting to add null entries");
    dictionary.add(null, null);
    dictionary.add("EX", null);
    dictionary.add(null, "EX");

    System.out.println("The list contains " + dictionary.getSize() +
                       " items, as follows: ");
    Iterator<String> keyIterator5 = dictionary.getKeyIterator();
    Iterator<String> valueIterator5 = dictionary.getValueIterator();
    while(keyIterator5.hasNext())
    {
      System.out.println(keyIterator5.next() + ", "  + valueIterator5.next());
    }
    System.out.println();
  } // end main
} // end of Demo
