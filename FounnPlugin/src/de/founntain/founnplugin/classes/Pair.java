package de.founntain.founnplugin.classes;

public class Pair<T, S> {
	
	private T item1;
	private S item2;
	
	public Pair(T item1, S item2){
		this.item1 = item1;
		this.item2 = item2;
	}
	
	public T GetItem1() {
		return this.item1;
	}
	
	public S GetItem2() {
		return this.item2;
	}
	
	public void SetItem1(T item1) {
		this.item1 = item1;
	}
	
	public void SetItem2(S item2) {
		this.item2 = item2;
	}
}
