package cn.edu.seu.datastruct.tree;

public class Node {

	private static final int CAPACITY = 10;
	private char data;
	private Node lchild;
	private Node rchild;

	public Node() {

	}

	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}

	public Node getRchild() {
		return rchild;
	}

	public void setRchild(Node rchild) {
		this.rchild = rchild;
	}

	public Node getLchild() {
		return lchild;
	}

	public void setLchild(Node lchild) {
		this.lchild = lchild;
	}

	public Node(char ch, Node rchild, Node lchild) {
		this.data = ch;
		this.rchild = rchild;
		this.lchild = lchild;
	}

	public String toString() {
		return "" + getData();
	}

	public Node createTree(String exp) {
		Node[] nodes = new Node[3];
		Node b, p = null;
		int top = -1, k = 0, j = 0;
		char[] exps = exp.toCharArray();
		char data = exps[j];
		b = null;
		while (j < exps.length - 1) {
			switch (data) {
			case '(':
				top++;
				nodes[top] = p;
				k = 1;
				break;
			case ')':
				top--;
				break;
			case ',':
				k = 2;
				break;
			default:
				p = new Node(data, null, null);
				if (b == null) {
					b = p;
				} else {
					switch (k) {
					case 1:
						nodes[top].setLchild(p);
						break;
					case 2:
						nodes[top].setRchild(p);
						break;
					}
				}
			}
			j++;
			data = exps[j];
		}
		return b;
	}

	/**
	 * pre order recursive
	 * 
	 * @param node
	 */
	public void PreOrder(Node node) {
		if (node == null) {
			return;
		} else {
			System.out.print(node.getData() + " ");
			PreOrder(node.getLchild());
			PreOrder(node.getRchild());

		}
	}

	/**
	 * in order recursive
	 * 
	 * @param node
	 */
	public void InOrder(Node node) {
		if (node == null) {
			return;
		} else {
			InOrder(node.getLchild());
			System.out.print(node.getData() + " ");
			InOrder(node.getRchild());
		}
	}

	/**
	 * post order recursive
	 * 
	 * @param node
	 */
	public void PostOrder(Node node) {
		if (node == null) {
			return;
		} else {
			PostOrder(node.getLchild());
			PostOrder(node.getRchild());
			System.out.print(node.getData() + " ");
		}
	}

	public void PreOrderNoRecursive(Node node) {
		Node nodes[] = new Node[CAPACITY];
		Node p = null;
		int top = -1;
		if (node != null) {
			top++;
			nodes[top] = node;
			while (top > -1) {
				p = nodes[top];
				top--;
				System.out.print(p.getData() + " ");
				if (p.getRchild() != null) {
					top++;
					nodes[top] = p.getRchild();
				}
				if (p.getLchild() != null) {
					top++;
					nodes[top] = p.getLchild();
				}
			}
		}
	}

	public void InOrderNoRecursive(Node node) {
		Node nodes[] = new Node[CAPACITY];
		Node p = null;
		int top = -1;
		if (node != null)
			p = node;
		while (p != null || top > -1) {
			while (p != null) {
				top++;
				nodes[top] = p;
				p = p.getLchild();
			}
			if (top > -1) {
				p = nodes[top];
				top--;
				System.out.print(p.getData() + " ");
				p = p.getRchild();
			}
		}
	}

	public void PostOrderNoRecursive(Node node) {
		Node[] nodes = new Node[CAPACITY];
		Node p = null;
		int flag = 0, top = -1;
		if (node != null) {
			do {
				while (node != null) {
					top++;
					nodes[top] = node;
					node = node.getLchild();
				}
				p = null;
				flag = 1;
				while (top != -1 && flag != 0) {
					node = nodes[top];
					if (node.getRchild() == p) {
						System.out.print(node.getData() + " ");
						top--;
						p = node;
					} else {
						node = node.getRchild();
						flag = 0;
					}
				}
			} while (top != -1);
		}
	}
}
