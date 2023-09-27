package ShoppingProject;

public class Clothes {

	String name;
	String brand;
	String color;
	int price;

	Clothes() {

	}

	Clothes(String name, String brand, String color, int price) {
		this.name = name;
		this.brand = brand;
		this.color = color;
		this.price = price;
	}

	public String toString() {
		return "Name: " + name + " Brand: " + brand + " Colour: " + color + " Price: " + price;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public int getPrice() {
		return price;
	}
}
