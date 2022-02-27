import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * @author trama
 *
 * @param <KeyType> the type of the given key
 * @param <ValueType> the type of the associated value of the given key 
 */
public class HashTableSortedSets<KeyType, ValueType extends Comparable<ValueType>>
		extends HashtableMap<KeyType, ValueType> implements IHashTableSortedSets<KeyType, ValueType> {
	

	/**
	 * Adding keys into hashtable
	 * @param key the given key
	 * @param value the associate value of the give key
	 */
	@Override
	public void add(KeyType key, ValueType value) {
		// get the index of the given key
		int keyIndex = getHashIndex(key);
		// create a list of values have same keys
		List<ValueType> tmp;
		// if key or value or null return immediately
		if (key == null || value == null) {
			return;
		}
		
		
		else if (arrays[keyIndex] == null) {
			// create new LinkedList at index
			arrays[keyIndex] = new LinkedList<HashNode<KeyType, List<ValueType>>>();
			// create a list of values have same keys
			tmp = new ArrayList<ValueType>();
			// add the first value into the list of values having same keys
			tmp.add(value);

			// create a node pair both key and list of values
			HashNode<KeyType, List<ValueType>> hashnode = new HashNode<KeyType, List<ValueType>>(key, tmp);

			// add the hashnode into hashtable
			arrays[keyIndex].add((HashNode<KeyType, List<ValueType>>) hashnode);
			// increment the size
			size++;
			// rehase if needed
			if ((double) size >= ((double) capacity * DEFAULT_CAPACITY)) {
				// resize the hastable
				resize();
			}
		} 
		
		else {
			// traverse the list of values
			for (int i = 0; i < arrays[keyIndex].size(); i++) {
				// if the key of arrays[keyIndex] equals the given key 
				if (arrays[keyIndex].contains(key)) {
					// adding the given value into the list of values
					tmp = arrays[keyIndex].get(i).getValue();

					int ind = 0;
					// sorting in the desending order
					for (ind = 0; ind < tmp.size(); ind++) {
						if (value.compareTo(tmp.get(ind)) < 0) {
							continue;
						}
						if (value.compareTo(tmp.get(ind)) >= 0) {
							break;
						}
					}
				}
			}
			// create a new LinkedList at index
			arrays[keyIndex] = new LinkedList<HashNode<KeyType, List<ValueType>>>();
			// create a new list of values
			tmp = new ArrayList<ValueType>();
			// add the value of the list of values
			tmp.add(value);
			
			HashNode<KeyType, List<ValueType>> hashnode = new HashNode<KeyType, List<ValueType>>(key, tmp);
			// add the hashnode into the hashtable
			arrays[keyIndex].add(hashnode);
			// increment the size
			size++;
		}

	}

}
