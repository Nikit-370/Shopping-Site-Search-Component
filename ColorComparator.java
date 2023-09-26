package ShoppingProject;

import java.util.Comparator;

public class ColorComparator implements Comparator<Clothes> {

	@Override
	public int compare(Clothes c1, Clothes c2) {
		return (c1.getColor()).compareTo(c2.getColor());
	}

}
