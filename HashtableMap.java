import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// --== CS400 Project One File Header ==--
// Name: Anh Thi Tram Tran
// CSL Username: anh
// Email: attran4@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: <any optional extra notes to your grader>
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, List<ValueType>>{

	// data fields
	protected int size;
	protected int capacity;
	protected final float DEFAULT_CAPACITY = 0.75f;

	protected LinkedList<HashNode<KeyType, List<ValueType>>>[] arrays;


	/**
	 * Default constructor 
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap() {
		// default capacity
		this.capacity = 20;
		// create empty LinkedList with size is 20
		arrays = new LinkedList[20];
		// set size to 0
		this.size = 0;
	}


	/**
	 * Constructor with assigned capacity
	 * 
	 * @param capacity the array size to store the key
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		this.capacity = capacity;
		// create an empty LinkedList with given capacity
		arrays = new LinkedList[capacity];
		// set size to 0
		this.size = 0;
	}

	
	/**
	 * Get the index by input key
	 * 
	 * @param key key value of the node
	 * @return the index of the key
	 */
	protected int getHashIndex(Object key) {
		return Math.abs(key.hashCode()) % capacity;
	}


	/**
	 * Resize the hash table arrays
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected void resize() {
		// double the capacity
		capacity = capacity * 2;
		// create a doubled capacity Hash Table
		LinkedList<HashNode<KeyType, List<ValueType>>>[] tmp = new LinkedList[capacity];
		
		// copy the existed key into appropriate index of new HashTable
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i] != null) {
				for (int index = 0; index < arrays[i].size(); index++) {
					if (arrays[i].get(index) != null) {
						KeyType key = arrays[i].get(index).getKey();
						ValueType value = (ValueType) arrays[i].get(index).getValue();
						HashNode<KeyType, ValueType> newNode = new HashNode<KeyType, ValueType>(key, value);
						int newIndex = Math.abs(key.hashCode()) % capacity;
						if (tmp[newIndex] == null) {
							tmp[newIndex] = new LinkedList<HashNode<KeyType, List<ValueType>>>();
							tmp[newIndex].add((HashNode<KeyType, List<ValueType>>) newNode);

						}
					}
				}
			}
		}
		// assign new HashTable as a current HashTable
		arrays = tmp;
	}

	

	/**
	 * Getting existed key inside the arrays
	 * @param key given key
	 * @throws NoSuchElementException 
	 * @return null if that hashnode doesn't exist; otherwise, return the value of that key
	 */
	public List<ValueType> get(Object key) throws NoSuchElementException {
		int index = getHashIndex(key);
		//System.out.println(index);
		//System.out.println(arrays.length);
		//System.out.println(arrays[index]);
		// check if the hashnode is null
		if (arrays[index] == null) {
			throw new NoSuchElementException("Cannot find the key.");
		}
		// otherwise
		for (int i = 0; i < arrays[index].size(); i++) {
			// check if they are equal
			if (arrays[index].get(i).getKey().equals(key)) {
				return (List<ValueType>) arrays[index].get(i).getValue();
			}
		}
		throw new NoSuchElementException("Cannot find the key.");
	}

	/**
	 * Checks if the key is existed in the array
	 * 
	 * @param key given key
	 * @return true if the key is found in the array; otherwise, return false
	 */
	public boolean containsKey(Object key) {
		int index = getHashIndex(key);
		// check if the hashnode is null
		if (arrays[index] == null) {
			return false;
		}

		for (int i = 0; i < arrays[index].size(); i++) {
			// check if they are equal
			if (arrays[index].get(i).getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove the existed key
	 * 
	 * @param key assigned key
	 * @return null if key doesn't exist; otherwise, return the value of that key
	 */
	@SuppressWarnings("unchecked")
	public List<ValueType> remove(Object key) {
		int index = getHashIndex(key);
		// check if the array includes that key
		if (!containsKey(key)) {
			return null;
		}

		// otherwise
		ValueType returnVal = null;
		for (int i = 0; i < arrays[index].size(); i++) {
			if (arrays[index].get(i).getKey().equals(key)) {
				returnVal = (ValueType) arrays[index].get(i).getValue();
				arrays[index].remove(i);
			}
		}
		// increment the size
		size--;
		return (List<ValueType>) returnVal;
	}

	/**
	 * Return the actual size
	 * @return the size of the array
	 */
	public int size() {
		return size;
	}

	/**
	 * Clear all the keys inside the array
	 *
	 */
	public void clear() {
		for (int i = 0; i < arrays.length; i++) {
			arrays[i] = null;
		}
		this.size = 0;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean put(KeyType key, List<ValueType> value) {
		int index = getHashIndex(key);
		HashNode<KeyType, ValueType> hashnode = new HashNode<KeyType, ValueType>((KeyType) key, (ValueType) value);
		// check if key is null or key is already existed
		if (key == null) {
			return false;
		}

		// otherwise
		else if (arrays[index] == null) {
			arrays[index] = new LinkedList<HashNode<KeyType, List<ValueType>>>();
			arrays[index].add((HashNode<KeyType, List<ValueType>>) hashnode);

		} else {
			arrays[index].add((HashNode<KeyType, List<ValueType>>) hashnode);

		}
		
		// increment the size
		size++;

		// check if the loadFactor exceeds the DEFAULT_CAPACITY
		if ((double) size >= ((double) capacity * DEFAULT_CAPACITY)) {
			// resize the hastable
			resize();
		}
		return true;

	}


}