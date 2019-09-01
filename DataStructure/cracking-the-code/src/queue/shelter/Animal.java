package queue.shelter;

public abstract class Animal {

	protected String name;
	protected int order;
	
	public Animal(String name) {
		this.name = name;
	}
	
	public abstract boolean isCat();
	
	public String getName() {
		return name;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
}
