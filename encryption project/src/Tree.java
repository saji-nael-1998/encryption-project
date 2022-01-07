
public class Tree {
	Node root;

	// Tree Node
	static class Node {
		int data;
		Node left, right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	// Function to insert nodes in level order
	public Node insertLevelOrder(int[] arr, Node root, int i) {
		// Base case for recursion
		if (i < arr.length) {
			Node temp = new Node(arr[i]);
			root = temp;

			// insert left child
			root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

			// insert right child
			root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
		}
		return root;
	}

	// Function to print tree nodes in InOrder fashion
	public String preOrder(Node root, String stepOne) {
		if (root != null) {
			stepOne += root.data;

			stepOne = preOrder(root.left, stepOne);
			stepOne = preOrder(root.right, stepOne);
		}
		return stepOne;
	}

	// Function to print tree nodes in InOrder fashion
	public String inOrder(Node root, String stepOne) {
		if (root != null) {

			stepOne = inOrder(root.left, stepOne);
			stepOne += root.data;
			stepOne = inOrder(root.right, stepOne);
		}
		return stepOne;
	}

	// Function to print tree nodes in InOrder fashion
	public String postOrder(Node root, String stepOne) {
		if (root != null) {
			

			stepOne = postOrder(root.left, stepOne);
			stepOne = postOrder(root.right, stepOne);
			stepOne += root.data;
		}
		return stepOne;
	}

}
