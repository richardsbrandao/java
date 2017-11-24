package vector;


public class Array {

	private String[] elements;
	private int size;
	
	public Array(Integer size) {
		this.elements = new String[size];
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(String theElement) {
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(theElement)) {
				return true;
			}
		}
		return false;
	}

	public void add(String element) {
		if( isCapacityReached() ) {
			increaseCapacity();
		}
		this.elements[size] = element;
		this.size++;
	}

	private void increaseCapacity() {
		String[] newElements = new String[this.elements.length*2];
		for(int i = 0; i < size; i++) {
			newElements[i] = this.elements[i];
		}
		this.elements = newElements;
	}

	private boolean isCapacityReached() {
		return this.elements.length == size;
	}

	public void addAll(Array array) {
		for(int i = 0; i < array.size(); i++) {
			add(array.get(i));
		}
	}

	public void clear() {
		this.elements = new String[elements.length];
		this.size = 0;
	}

	public String get(int index) {
		if( isNotAValidPosition(index) ) {
			return null;
		}
		return this.elements[index];
	}

	public void remove(int index) {
		if(isNotAValidPosition(index)) {
			return;
		}
		
		for( int i = index; i < size-1; i++) {
			this.elements[i] = this.elements[i+1]; 
		}
		this.elements[size-1] = null;
		this.size--;
	}

	private boolean isNotAValidPosition(int index) {
		return index < 0 || index > this.size;
	}

}
