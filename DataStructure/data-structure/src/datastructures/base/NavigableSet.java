package datastructures.base;

public interface NavigableSet<T> extends Set<T> {

	T highest(T key);
	T lowest(T key);
	
	NavigableSet<T> subSet(T from, T to, boolean inclusive);
	NavigableSet<T> tailSet(T from, boolean inclusive);
	NavigableSet<T> headSet(T from, boolean inclusive);
	
}
