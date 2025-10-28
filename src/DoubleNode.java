

public class DoubleNode<T> {
	Location element;
	DoubleNode<T> next;
	DoubleNode<T> previous;

	public DoubleNode(Location element) {
		setElement(element);
	}

	public Location getElement() {
		return element;
	}

	public void setElement(Location element) {
		this.element = element;
	}

	public DoubleNode<T> getNext() {
		return next;
	}

	public void setNext(DoubleNode<T> next) {
		this.next = next;
	}

	public DoubleNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DoubleNode<T> previous) {
		this.previous = previous;
	}

}
