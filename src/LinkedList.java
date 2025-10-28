import java.util.Date;

public class LinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	private int count;

	public LinkedList() {
		first = last = null;
	}

	public Object getFirst() {
		if (getCount() != 0)
			return first.element;
		else
			return null;
	}

	public Object getLast() {
		if (getCount() != 0)
			return last.element;
		else
			return null;
	}

	public void addFisrt(Martyer x) {
		if (getCount() == 0)
			first = last = new Node<T>(x);
		else {
			Node<T> temp = new Node<T>(x);
			temp.next = first;
			first = temp;
		}
		setCount(getCount() + 1);

	}

	public void addLast(Martyer x) {
		if (getCount() == 0)
			first = last = new Node<T>(x);
		else {
			Node<T> temp = new Node<T>(x);
			last.next = temp;
			last = temp;

		}
		setCount(getCount() + 1);

	}

	public void add(Martyer x, int index) {
		if (index == 0)
			addFisrt(x);
		else {
			if (index >= getCount())

				addLast(x);
			else {
				Node<T> temp = new Node<T>(x);
				Node<T> current = first;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;

				}
				temp.next = current.next;
				current.next = temp;
				setCount(getCount() + 1);

			}
		}

	}

	public Martyer get(int index) {

		Node<T> current = first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.element;

	}

	public boolean removeFirst() {
		if (getCount() == 0)
			return false;
		else {
			if (getCount() == 1)
				first = last = null;
			else {
				first = first.next;
				setCount(getCount() - 1);
			}
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
				Node<T> current = first;
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

				Node<T> current = first;
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
				Node<T> previous = first;
				Node<T> current = first.next;
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
		Node<T> temp = first;
		Martyer martyers;

		for (int i = 0; i < getCount(); i++) {
			martyers = (Martyer) temp.getElement();
			System.out.println(martyers.getName() + " " + martyers.getAge() + " " + martyers.getDateOfDeath() + " "
					+ martyers.getGender());
			temp = temp.next;
		}
	}

	public int search(T x) {
		Node<T> current = first;
		int index = 0;
		for (int i = 0; i < getCount(); i++) {
			if (current.element.equals(x) == true) {
				return index;
			}
			index++;
			current = current.next;

		}
		return -1;

	}

	public int search(Date x) {
		Node<T> current = first;
		int index = 0;
		for (int i = 0; i < getCount(); i++) {
			if (current.element.getDateOfDeath().equals(x) == true) {
				return index;
			}
			index++;
			current = current.next;

		}
		return -1;

	}

	public int search(String x) {
		Node<T> current = first;
		int index = 0;
		for (int i = 0; i < getCount(); i++) {
			if (((Martyer) current.getElement()).getName().equals(x)) {
				return index;
			}
			index++;
			current = current.next;

		}
		return -1;

	}

	public void sortList() {
		Node<T> current = null, index = null;
		Martyer temp;
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
		if (count == 0) {
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
