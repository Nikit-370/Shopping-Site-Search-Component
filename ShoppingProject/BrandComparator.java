package ShoppingProject;

import java.util.Comparator;

public class BrandComparator implements Comparator<Clothes> {

	@Override
	public int compare(Clothes c1, Clothes c2) {
		return c1.getBrand().compareTo(c2.getBrand());
	}

}
