
public class AVlTree1<T extends Comparable<T>> {
	AvlNode1<T> root;
	int counter = 0;

	public AVlTree1() {
		root = null;
	}

	AvlNode1<T> find(String x) {
		return (findRec(x, root));
	}

	private AvlNode1<T> findRec(String x, AvlNode1<T> t) {
		if (t == null) {
			return null;
		}
		// ((Martyer) current.getElement()).getName().equals(x)
		if (x.compareTo(t.element.getName()) < 0) {
			return (findRec(x, t.left));
		} else if (x.compareTo(t.element.getName()) > 0) {
			return (findRec(x, t.right));
		} else
			return t;
	}

	void insert(Martyer x) {
		root = insertRec(root, x);
	}

	private AvlNode1<T> insertRec(AvlNode1<T> node, Martyer x) {
		// check whether the node is null or not
		if (node == null) {
			node = new AvlNode1<T>(x);
			return node;
		}
		// insert a node in case when the given element is lesser than the element of
		// the root node
		if (x.getName().compareTo(node.element.getName()) < 0) {

			node.left = insertRec(node.left, x);
			if (getHeight(node.left) - getHeight(node.right) == 2)
				if (x.getName().compareTo(node.left.element.getName()) < 0)
					node = singleRotateLeft(node);
				else
					node = doubleRotateLeft(node);
		} else if (x.getName().compareTo(node.element.getName()) > 0) {
			node.right = insertRec(node.right, x);
			if (getHeight(node.right) - getHeight(node.left) == 2)
				if (x.getName().compareTo(node.right.element.getName()) > 0)
					node = singleRotateRight(node);
				else
					node = doubleRotateRight(node);
		} else
			; // if the element is already present in the tree, we will do nothing
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		return node;
	}

//	}

	void inOrder() {
		printInOrder(root);
	}

	void printInOrder(AvlNode1<T> t) {

		if (t != null) {
			counter++;
			printInOrder(t.left);
			System.out.println(t.element);
			printInOrder(t.right);
		}

	}

	AvlNode1<T> delete(Object x) {
		return deleteRec(root, x);
	}

	private AvlNode1<T> deleteRec(AvlNode1<T> t, Object x) {
		AvlNode1<T> child = null;
		AvlNode1<T> temp = null;
		if (t == null) {
			System.out.println("element not fond");
			return t;
		} else {
			if (((Martyer) x).getName().compareTo(t.element.getName()) < 0) {
				t.left = deleteRec(t.left, x);
			} else {
				if (((Martyer) x).getName().compareTo(t.element.getName()) > 0) {
					t.right = deleteRec(t.right, x);
				} else {
					if (t.left != null && t.right != null) {
						temp = findMinRec(t.right);
						t.element = temp.element;
						t.right = deleteRec(t.right, t.element);
					} else {
						if (t.left == null) {
							child = t.right;
						}
						if (t.right == null) {
							child = t.left;
						}
						return child;
					}
				}
			}
		}

		return t;
	}

	AvlNode1<T> findMin() {
		return findMinRec(root);
	}

	private AvlNode1<T> findMinRec(AvlNode1<T> t) {
		if (t == null) {
			return null;
		} else if (t.left == null) {
			return t;
		} else
			return findMinRec(t.left);
	}

	int getHeight(AvlNode1<T> p) {

		if (p == null)
			return -1;
		else
			return p.height;
	}

	int getHeight() {

		return getHeight(root);
	}

	AvlNode1<T> singleRotateLeft(AvlNode1<T> k2) {
		AvlNode1<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
		k1.height = Math.max(getHeight(k1.left), k2.height) + 1;
		return k1;
	}

	AvlNode1<T> doubleRotateLeft(AvlNode1<T> k3) {
		k3.left = singleRotateRight(k3.left);
		return singleRotateLeft(k3);
	}

	AvlNode1<T> doubleRotateRight(AvlNode1<T> k3) {
		k3.right = singleRotateLeft(k3.right);
		return singleRotateRight(k3);
	}

	AvlNode1<T> singleRotateRight(AvlNode1<T> k2) {
		AvlNode1<T> k1;
		k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
		k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
		return k1;
	}

	int check(AvlNode1<T> t) {
		if (t == null)
			return -1;
		else
			return Math.max(check(t.left), check(t.right) + 1);
	}

}
