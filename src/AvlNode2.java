
public class AvlNode2<T extends Comparable<T>> {
	DateStack element;
	AvlNode2<T> left;
	AvlNode2<T> right;
	int height;

	AvlNode2(DateStack element) {
		this.element = element;
		left = right = null;
		height = 0;
	}

}
