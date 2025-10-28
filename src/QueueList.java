
public class QueueList {
	private int size;

	private LinkedList<Martyer> linkedList;

	public QueueList() {
		linkedList = new LinkedList<>();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public Martyer dequeue() {
		if (isEmpty())
			return null;
		else {
			Martyer o = (Martyer) linkedList.getFirst();
			linkedList.removeFirst();
			size--;
			return o;
		}
	}

	public void enqueue(Martyer o) {
		size++;
		linkedList.addLast(o);
	}

	public LinkedList<Martyer> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(LinkedList<Martyer> linkedList) {
		this.linkedList = linkedList;
	}
	
	
}
