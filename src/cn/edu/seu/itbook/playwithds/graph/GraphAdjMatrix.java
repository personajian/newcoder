package cn.edu.seu.itbook.playwithds.graph;

import java.util.LinkedList;

public class GraphAdjMatrix {

	int[] vertexs;//顶点表-一维数组
	int[][] edges;//边表-二维数组
	int numVertexs;//顶点数
	int numEdges;//边数
	
	/**邻接矩阵的深度优先遍历
	 * 
	 */
	public void DFSTraverse() {
		boolean[] visited=new boolean[numVertexs];
		for (int i = 0; i < numVertexs; i++) {
			visited[i]=false;
		}
		
		for (int i = 0; i < numVertexs; i++) {
			if(!visited[i]) {
				DFS(i,visited);
			}
		}
	}

	/**邻接矩阵的深度优先递归算法
	 * @param i
	 * @param visited
	 */
	private void DFS(int i, boolean[] visited) {
		visited[i]=true;
		System.out.println(vertexs[i]);
		for (int j = 0; j < numVertexs; j++) {
			if(edges[i][j]==1&&!visited[j]) DFS(j, visited);//对未访问的邻接顶点递归调用
		}
	}
	
	public void BFSTraverse() {
		boolean[] visited=new boolean[numVertexs];
		LinkedList<Integer> queue=new LinkedList<>();
		for (int i = 0; i < numVertexs; i++) 
			visited[i]=true;
		for(int i=0;i<numVertexs;i++) {
			if(!visited[i]) {
				visited[i]=true;
				System.out.println(vertexs[i]);
				queue.offer(i);
				//队列不为空时，一直出队顶点元素
				while(!queue.isEmpty()) {
					i=queue.poll();
					for(int j=0;j<numVertexs;j++) {
						//寻找跟当前顶点有边，且未访问过的顶点；更新访问标志，访问之，入队。（相邻顶点全部入队）
						if(edges[i][j]==1&&!visited[j]) {
							visited[j]=true;
							System.out.println(vertexs[j]);
							queue.offer(j);
						}
					}
				}
				
			}
		}
		
	}

}
