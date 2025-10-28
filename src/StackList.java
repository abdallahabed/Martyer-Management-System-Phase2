

public class StackList {
	private LinkedList<Martyer> linkedList;

	public StackList() {
		linkedList = new LinkedList<>();
	}

	public boolean isEmpty() {
		return  linkedList.isEmpty();

	}

	public void push(Martyer o) {

		linkedList.addFisrt(o);

	}

	public Object pop() {
		if (!isEmpty()) {

			Object tmp = linkedList.getFirst();
			linkedList.removeFirst();
			
			return tmp;

		} else {
			System.out.println("stack is empty");
			return null;
		}
	}

	public Object top() {
		if (!isEmpty()) {
			return linkedList.getLast();
		} else {
			System.out.println("stack is empty");
			return null;
		}
	}

	public int size() {
		return linkedList.getCount();
	}

//	public boolean isFull() {
//		if (linkedList != null)
//			return false;
//	}

	public void print() {
		linkedList.printList();
	}

	public LinkedList<Martyer> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(LinkedList<Martyer> linkedList) {
		this.linkedList = linkedList;
	}

}
