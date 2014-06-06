package pl.edu.wat.wcy.tim.gorky.equipment;

/*
 * Zwykly przedmiot typu miksturka
 */

public abstract class Item {

	protected String name;
	
	public Item(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
