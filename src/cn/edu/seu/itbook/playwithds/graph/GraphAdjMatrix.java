package cn.edu.seu.itbook.playwithds.graph;

import java.util.LinkedList;

/**邻接矩阵的DFS和BFS实现，其他算法的实现都是基于此的扩展——例如访问节点时有其他操作，以及记录路径什么的。
 * @Author personajian
 * @Date 2017/8/16 9:26
 */
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
		//对所有的连通图调用DFS
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
		//对节点的操作，简化为打印，可以定制为其他操作
		System.out.println(vertexs[i]);
		for (int j = 0; j < numVertexs; j++) {
			if(edges[i][j]==1&&!visited[j]) //对未访问的邻接顶点递归调用
				DFS(j, visited);
		}
	}

	/**邻接矩阵的广度优先遍历算法，类似于树的层次遍历（利用辅助队列实现）
	 * @Param
	 * @Return
	 */
	public void BFSTraverse() {
		boolean[] visited=new boolean[numVertexs];
		LinkedList<Integer> queue=new LinkedList<>();
		for (int i = 0; i < numVertexs; i++) 
			visited[i]=false;

		//对所有的连通图依次调用BFS
		for(int i=0;i<numVertexs;i++) {
			//此处以下，类似于树的层次遍历
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
