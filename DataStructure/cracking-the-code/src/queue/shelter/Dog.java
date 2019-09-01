package queue.shelter;

public class Dog extends Animal {

	public Dog(String name) {
		super(name);
	}

	@Override
	public boolean isCat() {
		return false;
	}

}
