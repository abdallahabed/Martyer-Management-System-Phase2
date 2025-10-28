
public class AvlNode1<T extends Comparable<T>> {

	Martyer element;
	AvlNode1<T> left;
	AvlNode1<T> right;
	int height;

	AvlNode1(Martyer element) {
		this.element = element;
		left = right = null;
		height = 0;
	}
	
	
	

}
