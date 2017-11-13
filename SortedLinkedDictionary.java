import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
/**
   A dictionary using sorted linked data that implements DictionaryInterface.
   @author Minwoo Soh
*/
public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>
{
  private Node<K, V> firstNode;
  private int numberOfEntries;

  /** Create a SortedLinkedDictionary with no entries. */
  public SortedLinkedDictionary()
  {
    firstNode = null;
    numberOfEntries = 0;
  } // end constructor

  /** Adds a new entry to this dictionary. If the given search key already
      exists in the dictionary, replaces the corresponding value.
      @param key    An object search key of the new entry.
      @param value  An object associated with the search key.
      @return  Either null if the new entry was added to the dictionary
               or the value that was associated with key if that value
               was replaced. */
  public V add(K key, V value)
  {
    V result = null;
    try
    {
      if( (key == null) || (value == null) )
      {
        throw new IllegalStateException("IllegalStateException: Cannot enter null for keys and values. Entry is not added.");
      }
      else
      {
        Node<K, V> currentNode = firstNode;
        Node<K, V> nodeBefore = null;
        while ((currentNode != null) && (key.compareTo(currentNode.getKey()) > 0) )
        {
          nodeBefore = currentNode;
          currentNode = currentNode.getNextNode();
        } // end while

        // If key already exists.
        if ( (currentNode != null) && (key.equals(currentNode.getKey())) )
        {
          result = currentNode.getValue();
          currentNode.setValue(value);
        }
        else // If key is new.
        {
          Node newNode = new Node(key, value);
          if (nodeBefore == null) // If dictionary is empty.
          {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
          }
          else
          {
            newNode.setNextNode(currentNode);
            nodeBefore.setNextNode(newNode);
          } // end if
          numberOfEntries++;
        } // end if
      } // end if
    } // end try
    catch(IllegalStateException e)
    {
      System.out.println(e.getMessage());
    } // end catch
    return result;
  } // end add

  /** Removes a specific entry from this dictionary.
      @param key  An object search key of the entry to be removed.
      @return  Either the value that was associated with the search key
               or null if no such object exists. */
  public V remove(K key)
  {
    V result = null;
    Node<K, V> currentNode = firstNode;
    Node<K, V> nodeBefore = null;
    while ((currentNode != null) && (key.compareTo(currentNode.getKey()) != 0) )
    {
      nodeBefore = currentNode;
      currentNode = currentNode.getNextNode();
    } // end while

    if(currentNode != null) // If key is found.
    {
      if(currentNode == firstNode) // If key is the first node in the dictionary.
      {
        result = currentNode.getValue();
        firstNode = currentNode.getNextNode();
      }
      else // If key is after firstNode.
      {
        result = currentNode.getValue();
        nodeBefore.setNextNode(currentNode.getNextNode());
      } // end if
    } // end if
    return result;
  } // end remove

  /** Retrieves from this dictionary the value associated with a given
      search key.
      @param key  An object search key of the entry to be retrieved.
      @return  Either the value that is associated with the search key
               or null if no such object exists. */
  public V getValue(K key)
  {
    V result = null;
    Node<K, V> currentNode = firstNode;
    while ((currentNode != null) && (key.compareTo(currentNode.getKey()) != 0) )
    {
      currentNode = currentNode.getNextNode();
    } // end while

    if(currentNode != null) // If key is found.
    {
      result = currentNode.getValue();
    } // end if
    return result;
  } // end getValue

  /** Sees whether a specific entry is in this dictionary.
      @param key  An object search key of the desired entry.
      @return  True if key is associated with an entry in the dictionary. */
  public boolean contains(K key)
  {
    boolean result = false;
    Node<K, V> currentNode = firstNode;
    while ((currentNode != null) && (key.compareTo(currentNode.getKey()) != 0) )
    {
      currentNode = currentNode.getNextNode();
    } // end while

    if(currentNode != null)
    {
      result = true;
    } // end if
    return result;
  } // end contains

  /** Creates an iterator that traverses all search keys in this dictionary.
      @return  An iterator that provides sequential access to the search
               keys in the dictionary. */
  public Iterator<K> getKeyIterator()
  {
    ArrayList<K> keys = new ArrayList<K>();
    Node<K, V> currentNode = firstNode;
    while(currentNode != null)
    {
      keys.add(currentNode.getKey());
      currentNode = currentNode.getNextNode();
    }
    Iterator<K> keyIterator = keys.iterator();
    return keyIterator;
  } // end getKeyIterator

  /** Creates an iterator that traverses all values in this dictionary.
      @return  An iterator that provides sequential access to the values
               in this dictionary. */
  public Iterator<V> getValueIterator()
  {
    ArrayList<V> values = new ArrayList<V>();
    Node<K, V> currentNode = firstNode;
    while(currentNode != null)
    {
      values.add(currentNode.getValue());
      currentNode = currentNode.getNextNode();
    }
    Iterator<V> valueIterator = values.iterator();
    return valueIterator;
  } // end getValueIterator

  /** Sees whether this dictionary is empty.
      @return  True if the dictionary is empty. */
  public boolean isEmpty()
  {
    return numberOfEntries == 0;
  } // end isEmpty

  /** Gets the size of this dictionary.
      @return  The number of entries (key-value pairs) currently
               in the dictionary. */
  public int getSize()
  {
    return numberOfEntries;
  } // end getSize

  /** Removes all entries from this dictionary. */
  public void clear()
  {
    firstNode = null;
    numberOfEntries = 0;
  } // end clear

  /** Private inner class Node. */
  private class Node<K, V>
  {
    private K key;
    private V value;
    private Node next;

    /** Creates an instance that contains the key and value.
        Thew new node points to null.
        @param key  Set key for new Entry.
        @param value  Set value for new Entry. */
    private Node(K key, V value)
    {
      this(key, value, null);
    } // end constructor

    /** Creates an instance that contains the key and value.
        The new Node points to another node.
        @param key  Set key for new Entry.
        @param value  Set value for new Entry. */
    private Node(K key, V value, Node next)
    {
      this.key = key;
      this.value = value;
      this.next = next;
    } // end constructor

    /** Returns the key.
        @return  Key of Entry. */
    private K getKey()
    {
      return key;
    } // end getKey

    /** Returns the value.
        @return  Value of Entry. */
    private V getValue()
    {
      return value;
    } // end getValue

    /** Returns the next Node. */
    private Node getNextNode()
    {
      return next;
    } // end getNextNode

    /** Sets a new value that will replace the old value.
        @param value  The new value to be set. */
    private void setValue(V value)
    {
      this.value = value;
    } // end setValue

    /** Sets another Node for next.
        @param next  The new next Node to be set. */
    private void setNextNode(Node next)
    {
      this.next = next;
    } // end setValue
  } // end Node
} // end of SortedLinkedDictionary
