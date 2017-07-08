package cn.edu.seu.itbook.algorithm4.graph;

/**
 * @author personajian
 * Floyd算法所有顶点之间的最短路径。
 * 基于动态规划的思想：从i号顶点到j号顶点只经过前k号顶点的最短路径。
 */
public class Floyd {
	
	/**Floyd算法，求网图G中各顶点v到其余顶点w最短路径P[v][w]及带权长度D[v][w]
	 * @param G 图
	 * @param P P[v]的值为前驱顶点的下标
	 * @param D D[v]的值为v0到v的最短路径长度。
	 */
	public static void shortestPathFloyd(MGraph G,int[][] P,int[][] D) {
		
		int v,w,k;
		
		//初始化数据
		for(v=0;v<G.VERTEXNUM;v++) {
			for(w=0;w<G.VERTEXNUM;w++) {
				D[v][w]=G.edge[v][w];//对应顶点之间的权值
				P[v][w]=w;//路径数组，存储顶点
			}
		}
		//Floyd核心代码五行
		for(k=0;k<G.VERTEXNUM;k++) {
			for(v=0;v<G.VERTEXNUM;v++) {
				for(w=0;w<G.VERTEXNUM;w++) {
					if(D[v][w]>D[v][k]+D[k][w]) {
						D[v][w]=D[v][k]+D[k][w];//将当前两顶点权值设置为更小的一个
						P[v][w]=P[v][k];//路径设置经过下标为k的顶点
					}
				}
			}
		}
	}
	
	public static void main(String[] arg) {
		int I =65535;
		int vertexnum=5;
		int[][] edge=new int[vertexnum][vertexnum];
		edge[0]= new int []{0,10,I,I,5};
		edge[1]= new int []{I,0,1,I,2};
		edge[2]= new int []{I,I,0,4,I};
		edge[3]= new int []{7,I,6,0,I};
		edge[4]= new int []{I,3,9,2,0};
		MGraph G=new MGraph(vertexnum,edge);
		int[][] pathMatrix=new int[vertexnum][vertexnum];
		int[][] shortPathTable=new int[vertexnum][vertexnum];
		shortestPathFloyd(G,pathMatrix,shortPathTable);
		/*for(int i:shortPathTable) {
			System.out.print(i+" ");
		}*/
	}
	
}

