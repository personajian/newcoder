package cn.edu.seu.itbook.playwithds.graph;

import java.util.LinkedList;

public class GraphAdjList {

	/**
	 * @author personajian
	 * 边表（链表）
	 */
	private static class EdgeNode{
		int adjvex;
		int weight;//自定义数据域：路径权值
		EdgeNode next;
	}
	
	/**
	 * @author personajian
	 * 顶点：包括数据域和边表引用
	 */
	private static class VertexNode{
		int data;
		EdgeNode firstEdge;
	}
	
	VertexNode[] adjList;
	int numVertexs;
	int numEdges;
	
	/**邻接表的深度优先遍历
	 * 
	 */
	public void DFSTraverse() {
		boolean[] visited = new boolean[numVertexs];
		for(int i=0;i<numVertexs;i++) {
			visited[i]=false;
		}
		for(int i=0;i<numVertexs;i++) {
			if(!visited[i]) DFS(i,visited);//对未访问的顶点调用DFS，若是连通图只会执行一次
		}
		
	}

	/**邻接表的深度优先递归算法
	 * @param i 顶点号
	 * @param visited 访问标志数组
	 */
	private void DFS(int i,boolean[] visited) {
		EdgeNode p;
		visited[i]=true;
		System.out.println(adjList[i].data);
		
		p=adjList[i].firstEdge;
		
		while(p!=null) {
			if(!visited[p.adjvex]) DFS(p.adjvex, visited);//对未访问的邻接顶点递归调用
			p=p.next;
		}
	}
	
	/**
	 * 邻接表的广度遍历算法
	 */
	public void BFSTraverse() {
		EdgeNode p;
		boolean[] visited=new boolean[numVertexs];//初始化访问标志的数组
		LinkedList<Integer> queue=new LinkedList<>();//存放顶点的队列
		for(int i=0;i<numVertexs;i++) {
			visited[i]=false;
		}
		
		for (int i = 0; i < numVertexs; i++) {// 对每一个顶点作循环
			if (!visited[i]) {
				visited[i] = true;
				System.out.println(adjList[i].data);
				queue.offer(i);// 此顶点编号入队
				while (!queue.isEmpty()) {//队列不为空就一直出队元素进行访问
					i = queue.poll();
					p = adjList[i].firstEdge;// 找到当前顶点边链表的引用
					while (p != null) {
						if (!visited[p.adjvex]) {// 若此顶点未被访问过
							visited[p.adjvex] = true;
							System.out.println(adjList[p.adjvex].data);
							queue.offer(p.adjvex);// 将此顶点入队
						}
						p = p.next;// 下一个邻接点
					}
				}
			}
		}
		
	}
}
