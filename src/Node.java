

public class Node<T> {
	Martyer element;
	Node<T> next;

	public Node(Martyer element) {
		setElement(element);
	}

	public Martyer getElement() {
		return element;
	}

	public void setElement(Martyer element) {
		this.element = element;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

}
