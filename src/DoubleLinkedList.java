
public class DoubleLinkedList<T> {

	private DoubleNode<T> first;
	private DoubleNode<T> last;
	private int count;

	public DoubleLinkedList() {
	}

	public Object getFirst() {
		if (getCount() != 0) {
			return first.element;
		} else
			return null;
	}

	public Object getLast() {
		if (getCount() != 0)
			return last.element;
		else
			return null;
	}

	public Location get(int index) {

		DoubleNode<T> current = first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.element;

	}

	public void addFisrt(Location x) {
		if (getCount() == 0)
			first = last = new DoubleNode<T>(x);
		else {
			DoubleNode<T> temp = new DoubleNode<T>(x);
			temp.next = first;
			first = temp;
		}
		setCount(getCount() + 1);

	}

	public void addLast(Location x) {
		if (getCount() == 0)
			first = last = new DoubleNode<T>(x);
		else {
			DoubleNode<T> temp = new DoubleNode<T>(x);
			last.next = temp;
			last = temp;

		}
		setCount(getCount() + 1);

	}

	public void add(Location x, int index) {
		if (index == 0)
			addFisrt(x);
		else {
			if (index >= getCount())

				addLast(x);
			else {
				DoubleNode<T> temp = new DoubleNode<T>(x);
				DoubleNode<T> current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;

				}
				temp.next = current.next;
				temp.previous = current;
				current.next = temp;
				setCount(getCount() + 1);

			}
		}

	}

	public boolean removeFirst() {
		if (getCount() == 0)
			return false;
		else {
			if (getCount() == 1)
				first = last = null;
			else {
				first = first.next;
				first.next.previous = null;
			}
			setCount(getCount() - 1);
			return true;

		}

	}

	public boolean removeLast() {
		if (getCount() == 0)
			return false;
		else {
			if (getCount() == 1)
				first = last = null;
			else {
				DoubleNode<T> current = first;
				for (int i = 0; i < getCount() - 2; i++) {
					current = current.next;
				}
				last = current;
				current.next = null;
			}
			setCount(getCount() - 1);
			return true;

		}

	}

	public boolean remove(int index) {

		if (getCount() == 0)
			return false;
		else {

			if (index == 0)
				return removeFirst();
			else if (index == getCount() - 1)
				return removeLast();
			else if (index <= 0 || index > getCount())
				return false;
			else {

				DoubleNode<T> current = first;
				for (int i = 0; i < index - 1; i++) {

					current = current.next;

				}
				current.next = (current.next).next;
				setCount(getCount() - 1);
			}
			return true;
		}

	}

	public boolean remove(Object x) {
		if (getCount() == 0)
			return false;
		else {
			if (first.element.equals(x) == true)
				return removeFirst();
			else {
				DoubleNode<T> previous = first;
				DoubleNode<T> current = first.next;
				while (current != null && current.element.equals(x) == false) {
					previous = current;
					current = current.next;
				}
				if (current != null) {
					previous.next = current.next;
					setCount(getCount() - 1);
					return true;
				}
			}
			return false;
		}

	}

	public void printList() {
		DoubleNode<T> temp = first;
		Location location;
		AVlTree1<Martyer> martyers;
		for (int i = 0; i < getCount(); i++) {
			location = (Location) temp.getElement();
			martyers = location.getaVlTree1();
			martyers.inOrder();
			temp = temp.next;
		}
	}

	public int search(T x) {
		int index = 0;
		DoubleNode<T> current = first;
		for (int i = 0; i < getCount(); i++) {
			if (current.element.equals(x) == true) {
				return index;
			}
			index++;
			current = current.next;

		}
		return -1;

	}

	public int search(String location) {
		int index = 0;
		DoubleNode<T> current = first;
		for (int i = 0; i < getCount(); i++) {
			if (((Location) current.getElement()).getLocation().equals(location)) {
				return index;
			}
			index++;
			current = current.next;

		}
		return -1;

	}

	public void sortList() {
		DoubleNode<T> current = null, index = null;
		Location temp;
		// Check whether list is empty
		if (first == null) {
			return;
		} else {
			// Current will point to head
			for (current = first; current.next != null; current = current.next) {
				// Index will point to node next to current
				for (index = current.next; index != null; index = index.next) {
					// If current's data is greater than index's data, swap the data of current and
					// index

					if (current.getElement().compareTo(index.getElement()) == 1) {
						temp = current.getElement();
						current.setElement(index.getElement());
						index.setElement(temp);
					}
				}
			}
		}
	}

	public boolean isEmpty() {
		if (this.getCount() == 0) {
			return true;
		}
		return false;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
