import java.util.Date;

public class AvlTree2<T extends Comparable<T>> {
	AvlNode2<T> root;

	public AvlTree2() {
		root = null;
	}

	AvlNode2<T> find(Date x) {
		return (findRec(x, root));
	}

	private AvlNode2<T> findRec(Date x, AvlNode2<T> t) {
		if (t == null) {
			return null;
		}
		if (x.compareTo(t.element.getDate()) < 0) {
			return (findRec(x, t.left));
		} else if (x.compareTo(t.element.getDate()) > 0) {
			return (findRec(x, t.right));
		} else
			return t;
	}

	void insert(DateStack x) {
		root = insertRec(root, x);
	}

	private AvlNode2<T> insertRec(AvlNode2<T> node, DateStack x) {
		// check whether the node is null or not
		if (node == null) {
			node = new AvlNode2<T>(x);
			return node;
		}
		// insert a node in case when the given element is lesser than the element of
		// the root node
		if (x.getDate().compareTo(node.element.getDate()) < 0) {

			node.left = insertRec(node.left, x);
			if (getHeight(node.left) - getHeight(node.right) == 2)
				if (x.getDate().compareTo(node.left.element.getDate()) < 0)
					node = singleRotateLeft(node);
				else
					node = doubleRotateLeft(node);
		} else if (x.getDate().compareTo(node.element.getDate()) > 0) {
			node.right = insertRec(node.right, x);
			if (getHeight(node.right) - getHeight(node.left) == 2)
				if (x.getDate().compareTo(node.right.element.getDate()) > 0)
					node = singleRotateRight(node);
				else
					node = doubleRotateRight(node);
		} else
			; // if the element is already present in the tree, we will do nothing
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		return node;
	}

	void inOrder() {
		printInOrder(root);
	}

	void printInOrder(AvlNode2<T> t) {

		if (t != null) {
			printInOrder(t.left);
			System.out.println(t.element.getDate());
			printInOrder(t.right);
		}

	}

	AvlNode2<T> delete(DateStack x) {
		return deleteRec(root, x);
	}

	private AvlNode2<T> deleteRec(AvlNode2<T> t, DateStack x) {
		AvlNode2<T> temp = null;
		AvlNode2<T> child = null;
		if (t == null) {
			System.out.println(" not exist");
			return null;
		}

		if (x.getDate().compareTo(t.element.getDate()) < 0) {

			t.left = deleteRec(t.left, x);
		}

		if (x.compareTo(t.element) > 0) {
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
		return t;

	}

	AvlNode2<T> findMin() {
		return findMinRec(root);
	}

	private AvlNode2<T> findMinRec(AvlNode2<T> t) {
		if (t == null) {
			return null;
		} else if (t.left == null) {
			return t;
		} else
			return findMinRec(t.left);
	}

	int getHeight(AvlNode2<T> p) {

		if (p == null)
			return -1;
		else
			return p.height;
	}

	int getHeight() {

		return getHeight(root);
	}

	AvlNode2<T> singleRotateLeft(AvlNode2<T> k2) {
		AvlNode2<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
		k1.height = Math.max(getHeight(k1.left), k2.height) + 1;
		return k1;
	}

	AvlNode2<T> doubleRotateLeft(AvlNode2<T> k3) {
		k3.left = singleRotateRight(k3.left);
		return singleRotateLeft(k3);
	}

	AvlNode2<T> doubleRotateRight(AvlNode2<T> k3) {
		k3.right = singleRotateLeft(k3.right);
		return singleRotateRight(k3);
	}

	AvlNode2<T> singleRotateRight(AvlNode2<T> k2) {
		AvlNode2<T> k1;
		k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(getHeight(k2.left), getHeight(k2.right)) + 1;
		k1.height = Math.max(getHeight(k1.left), getHeight(k1.right)) + 1;
		return k1;
	}

	int check(AvlNode2<T> t) {
		if (t == null)
			return -1;
		else
			return Math.max(check(t.left), check(t.right) + 1);
	}

}
