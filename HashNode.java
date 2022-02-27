// --== CS400 Project One File Header ==--
// Name: Anh Thi Tram Tran
// CSL Username: anh
// Email: attran4@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: <any optional extra notes to your grader>
public class HashNode<KeyType, ValueType> {
	private KeyType key;
	private ValueType value;
	private HashNode<KeyType, ValueType> next;

	public HashNode<KeyType, ValueType> getNext() {
		return next;
	}

	public void setNext(HashNode<KeyType, ValueType> next) {
		this.next = next;
	}

	public HashNode(KeyType key, ValueType value) { 
		this.key = key;
		this.value = value;
	}

	public ValueType getValue() {
		return value;
	}

	public void setValue(ValueType value) {
		this.value = value;
	}

	public KeyType getKey() {
		return key;
	}
}

