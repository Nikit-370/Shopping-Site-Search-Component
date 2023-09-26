package ShoppingProject;

import java.util.Comparator;

public class PriceComparator implements Comparator<Clothes> {

	@Override
	public int compare(Clothes c1, Clothes c2) {
		return c1.price - c2.price;
	}
}
