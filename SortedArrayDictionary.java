import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
/**
   A dictionary using resizable sorted array that implements DictionaryInterface.
   @author Minwoo Soh
*/
public class SortedArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>
{
  private Entry<K, V>[] dictionary;
  private int numberOfEntries;
  private final static int DEFAULT_CAPACITY = 25;
  private static final int MAX_CAPACITY = 10000;
  private boolean initialized = false;

  /** Creates an empty dictionary array with the default capacity of 10. */
  public SortedArrayDictionary()
  {
    this(DEFAULT_CAPACITY);
  } // end default constructor

  /** Creates an empty dictionary array with specificed capacity.
      @param initialCapacity  The wanted size of the array capacity. */
  public SortedArrayDictionary(int initialCapacity)
  {
    checkCapacity(initialCapacity);
    @SuppressWarnings("unchecked")
    Entry<K, V>[] tempDict = (Entry<K, V>[])new Entry[initialCapacity];
    dictionary = tempDict;
    numberOfEntries = 0;
    initialized = true;
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
    checkInitialization();
    V result = null;
    try
    {
      if( (key == null) || (value == null) )
      {
        throw new IllegalStateException("IllegalStateException: Cannot enter null for keys and values. Entry is not added.");
      }
      else
      {
        int keyIndex = locateIndex(key);
        if((keyIndex < numberOfEntries) && (key.equals(dictionary[keyIndex].getKey())) )
        {
          // If key already exists in the dictionary.
          result = dictionary[keyIndex].getValue();
          dictionary[keyIndex].setValue(value);
        }
        else // If key is new.
        {
          makeRoom(keyIndex);
          dictionary[keyIndex] = new Entry<>(key, value);
          numberOfEntries++;
        } // end if
        ensureCapacity();
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
    checkInitialization();
    V result = null;
    int keyIndex = locateIndex(key);
    if(keyIndex < numberOfEntries) // If key is found.
    {
      result = dictionary[keyIndex].getValue();
      dictionary[keyIndex] = null;
      removeGap(keyIndex);
      numberOfEntries--;
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
    checkInitialization();
    V result = null;
    int keyIndex = locateIndex(key);
    if(keyIndex < numberOfEntries) // If key is found.
    {
      result = dictionary[keyIndex].getValue();
    } // end if
    return result;
  } // end getValue

  /** Sees whether a specific entry is in this dictionary.
      @param key  An object search key of the desired entry.
      @return  True if key is associated with an entry in the dictionary. */
  public boolean contains(K key)
  {
    checkInitialization();
    boolean result = false;
    int keyIndex = locateIndex(key);
    if(key.compareTo(dictionary[keyIndex].getKey()) == 0)
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
    checkInitialization();
    ArrayList<K> keys = new ArrayList<K>();
    for(int i = 0; i < numberOfEntries; i++)
    {
      keys.add(dictionary[i].getKey());
    } // end for
    Iterator<K> keyIterator = keys.iterator();
    return keyIterator;
  } // end getKeyIterator

  /** Creates an iterator that traverses all values in this dictionary.
      @return  An iterator that provides sequential access to the values
               in this dictionary. */
  public Iterator<V> getValueIterator()
  {
    checkInitialization();
    ArrayList<V> values = new ArrayList<V>();
    for(int i = 0; i < numberOfEntries; i++)
    {
      values.add(dictionary[i].getValue());
    } // end for
    Iterator<V> valueIterator = values.iterator();
    return valueIterator;
  } // end getValueIterator

  /** Sees whether this dictionary is empty.
      @return  True if the dictionary is empty. */
  public boolean isEmpty()
  {
    checkInitialization();
    return numberOfEntries == 0;
  } // end isEmpty

  /** Gets the size of this dictionary.
      @return  The number of entries (key-value pairs) currently
               in the dictionary. */
  public int getSize()
  {
    checkInitialization();
    return numberOfEntries;
  } // end getSize

  /** Removes all entries from this dictionary. */
  public void clear()
  {
    checkInitialization();
    for(int i = 0; i < numberOfEntries; i++)
    {
      dictionary[i] = null;
    } // end for
    numberOfEntries = 0;
  } // end clear

  /** Searches dictionary for the requested item and returns the position.
      If none is found, last index is returned.
      @param key   */
  private int locateIndex(K key)
  {
    int index = 0;
    while( (index < numberOfEntries) && (key.compareTo(dictionary[index].getKey()) > 0) )
    {
      index++;
    } // end while
    return index;
  } // end locateIndex

  /** Makes room for a new entry at position. Shifts entries after position.
      @param position  The position where new entry will be inserted. */
  private void makeRoom(int position)
  {
    int newIndex = position;
    int lastIndex = numberOfEntries - 1;

    for(int i = lastIndex; i >= newIndex; i--)
    {
      dictionary[i + 1] = dictionary[i];
    } // end for
  } // end makeRoom

  /** Shifts entries that are beyond the entry to be removed to the next lower position.
      @param position  The position where entry was removed. */
  private void removeGap(int position)
  {
    int removedIndex = position;
    int lastIndex = numberOfEntries - 1;

    for(int i = removedIndex; i < lastIndex; i++)
    {
      dictionary[i] = dictionary[i + 1];
    } // end for

    dictionary[lastIndex] = null;
  } // end removeGap

  /** Throws an exception if the user requests a capacity that is too large.
      @param capacity  The size of requested array. */
  private void checkCapacity(int capacity)
  {
    if (capacity > MAX_CAPACITY)
    {
      throw new IllegalStateException("Attempt to create a dictionary whose capacity " +
                                      "exceeds allowed maximum of " + MAX_CAPACITY);
    } // end if
  } // end checkCapacity

  /** Doubles the size of the array if it is full. */
  private void ensureCapacity()
  {
    if(numberOfEntries == dictionary.length)
    {
      int newLength = 2 * dictionary.length;
      checkCapacity(newLength);
      dictionary = Arrays.copyOf(dictionary, newLength);
    } // end if
  } // end ensureCapacity

  /** Throws an exception if the object is not initialized. */
  private void checkInitialization()
  {
    if(!initialized)
    {
      throw new SecurityException("SortedArrayDictionary object is not initialized properly.");
    } // end if
  } // end checkInitialization

  /** Private inner class Entry. */
  private class Entry<K, V>
  {
    private K key;
    private V value;

    /** Creates an instance that contains the key and value.
        @param key  Set key for new Entry.
        @param value  Set value for new Entry. */
    private Entry(K key, V value)
    {
      this.key = key;
      this.value = value;
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

    /** Sets a new value that will replace the old value.
        @param value  The new value to be set. */
    private void setValue(V value)
    {
      this.value = value;
    } // end setValue
  } // end of Entry
} // end of SortedArrayDictionary
