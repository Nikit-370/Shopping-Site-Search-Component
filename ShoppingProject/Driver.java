package ShoppingProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Driver {

	static List<Clothes> clothList = new Vector<Clothes>();
	static List<Clothes> wishList = new Vector<Clothes>();
	static List<Clothes> sortedList;

	static boolean flag = true;
	static String choice;
	static String filterChoice;

	public static void main(String[] args) {
		initializeClothList();
		runShoppingApp();
	}

	public static void initializeClothList() {
		clothList.add(new Clothes("Shirt", "Allen_Solly", "White", 2080));
		clothList.add(new Clothes("Shirt", "Allen_Solly", "White", 2080));
		clothList.add(new Clothes("Shirt", "Arrow", "Black", 2000));
		clothList.add(new Clothes("Shirt", "Adidas", "Green", 1400));
		clothList.add(new Clothes("Shirt", "Van_Huesan", "Blue", 1080));
		clothList.add(new Clothes("Shirt", "Nike", "Grey", 3000));

		clothList.add(new Clothes("Jeans", "Denim", "Navy_Blue", 1200));
		clothList.add(new Clothes("Jeans", "Diesel", "Black", 1800));
		clothList.add(new Clothes("Jeans", "Wrangler", "Blue", 2000));
		clothList.add(new Clothes("Jeans", "Levis", "Matt_Black", 1600));
		clothList.add(new Clothes("Jeans", "Lee_Cooper", "Grey", 1900));

		clothList.add(new Clothes("Tshirt", "Diesel", "Red", 1000));
		clothList.add(new Clothes("Tshirt", "Adidas", "Green", 1200));
		clothList.add(new Clothes("Tshirt", "Nike", "White", 1500));
		clothList.add(new Clothes("Tshirt", "Allen_Solly", "Black", 1080));
		clothList.add(new Clothes("Tshirt", "Levis", "Blue", 1900));
	}

	public static void runShoppingApp() {
		while (true) {
			try {
				if (flag)
					filterClothes();
				else
					break;
			} catch (Exception e) {
				System.err.println("An error occurred: " + e.getMessage());
				System.err.println("Please try again.");
			}
		}
	}

	public static void filterClothes() {
		try {
			System.out.println("=========================================");
			Scanner sc = new Scanner(System.in);
			System.out.println("Welcome to our Shop");
			System.out.println(
					"Please select the cloth you want to buy: \nShirt\nJeans\nTshirt\n>Shirt_sortBrand/Price/Color\n>Jeans_sortBrand/Price/Color\n>Tshirt_sortBrand/Price/Color\n>WishList\n>exit");
			choice = sc.next();

			if (choice.equals("exit")) {
				System.out.println("Visit us again! Thank You.");
				flag = false;
			} else if (choice.equalsIgnoreCase("wishlist")) {
				displayWishlist();
			} else if (containsChoice(clothList, choice) || choice.contains("sort")) {
				processClothingChoice();
			} else {
				System.err.println("Invalid Option. Try Again");
			}
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			System.err.println("Please try again.");
		}
	}

	public static void processClothingChoice() {
		System.out.println("=========================================");
		System.out.println("You selected: " + choice);
		if (choice.contains("sort")) {
			sort(choice);
		} else {
			displayClothesByType(choice);
		}
	}

	public static void displayClothesByType(String selectedType) {
		for (Clothes i : clothList) {
			if (i.toString().contains(selectedType)) {
				System.out.printf("Name:%-10sBrand:%-12sColour:%-12sPrice:%-8s%n", i.getName(), i.getBrand(),
						i.getColor(), i.getPrice());
			}
		}
		selectAndAddToWishlist(selectedType);
	}

	public static void selectAndAddToWishlist(String selectedType) {
		System.out.println("=========================================");
		System.out.println("Enter the Brand / Color / Price you want to buy: ");
		Scanner sc = new Scanner(System.in);
		filterChoice = sc.next();
		System.out.println("=========================================");
		System.out.println("You selected: " + filterChoice);

		for (Clothes i : clothList) {
			if (i.toString().contains(selectedType) && i.toString().contains(filterChoice)) {
				System.out.println(i);
				wishList.add(i);
				System.out.println("Item Added to Wishlist");
			}
		}
	}

	public static void sort(String choice) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("=========================================");
			System.out.println("You selected: " + choice);

			choice.toString().contains("_sortPrice");
			if (choice.contains("_sortPrice")) {
				sortedList = new ArrayList<Clothes>();
				for (Clothes item : clothList) {
					if (item.getName().equalsIgnoreCase(choice.replace("_sortPrice", ""))) {
						sortedList.add(item);
					}
				}
				Collections.sort(sortedList, new PriceComparator());

				for (Clothes i : sortedList) {
					System.out.printf("Name:%-10sBrand:%-12sColour:%-12sPrice:%-8s%n", i.getName(), i.getBrand(),
							i.getColor(), i.getPrice());
				}
			} else if (choice.contains("_sortBrand")) {
				sortedList = new ArrayList<Clothes>();
				for (Clothes item : clothList) {
					if (item.getName().equalsIgnoreCase(choice.replace("_sortBrand", ""))) {
						sortedList.add(item);
					}
				}
				Collections.sort(sortedList, new BrandComparator());

				for (Clothes i : sortedList) {
					System.out.printf("Name:%-10sBrand:%-12sColour:%-12sPrice:%-8s%n", i.getName(), i.getBrand(),
							i.getColor(), i.getPrice());
				}
			} else if (choice.contains("_sortColor")) {
				sortedList = new ArrayList<Clothes>();
				for (Clothes item : clothList) {
					if (item.getName().equalsIgnoreCase(choice.replace("_sortColor", ""))) {
						sortedList.add(item);
					}
				}
				Collections.sort(sortedList, new ColorComparator());

				for (Clothes i : sortedList) {
					System.out.printf("Name:%-10sBrand:%-12sColour:%-12sPrice:%-8s%n", i.getName(), i.getBrand(),
							i.getColor(), i.getPrice());
				}
			}

			System.out.println("Enter the Brand / Color / Price you want to buy: ");
			filterChoice = sc.next();

			System.out.println("=========================================");
			System.out.println("You selected: " + filterChoice);

			if (sortedList.toString().contains(filterChoice)) {
				for (Clothes i : sortedList) {
					if (i.getBrand().equalsIgnoreCase(filterChoice) || i.getColor().equalsIgnoreCase(filterChoice)
							|| String.valueOf(i.getPrice()).equalsIgnoreCase(filterChoice)) {
						System.out.println(i);
						wishList.add(i);
						System.out.println("Item Added to Wishlist");
					}
				}
			} else if (filterChoice.equalsIgnoreCase("wishlist")) {
				if (!wishList.isEmpty()) {
					for (Clothes i : wishList) {
						System.out.printf("Name:%-10sBrand:%-12sColour:%-12sPrice:%-8s%n", i.getName(), i.getBrand(),
								i.getColor(), i.getPrice());
					}
				} else {
					System.out.println("Wishlist is Empty");
				}
			} else if (filterChoice.equalsIgnoreCase("exit")) {
				flag = false;
				System.out.println("Visit us again! Thank You.");
			}
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
			System.err.println("Please try again.");
		}
	}

	public static void displayWishlist() {
		if (!wishList.isEmpty()) {
			for (Clothes i : wishList) {
				System.out.printf("Name:%-10sBrand:%-12sColour:%-12sPrice:%-8s%n", i.getName(), i.getBrand(),
						i.getColor(), i.getPrice());
			}
		} else {
			System.out.println("Wishlist is Empty");
		}
	}

	public static boolean containsChoice(List<Clothes> list, String choice) {
		for (Clothes i : list) {
			if (i.toString().contains(choice)) {
				return true;
			}
		}
		return false;
	}
}
