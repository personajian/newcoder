package cn.edu.seu.itbook.algorithm4.graph;

/**
 * @author personajian
 * Dijkstra算法求单源最短路径。
 */
public class Dijkstra {
	
	/**
	 * @param G 图
	 * @param v0 源点v0
	 * @param P P[v]的值为前驱顶点的下标
	 * @param D D[v]的值为v0到v的最短路径长度。
	 */
	public static void shortestPathDij(MGraph G,int v0,int[] P,int[] D) {
		
		int v,w,k=0,min;
		
		boolean[] finall=new boolean[G.VERTEXNUM];//final[v]=true表示已经求得了v0到v的最短路径
		
		for(v=0;v<G.VERTEXNUM;v++) {//初始化数据
			finall[v]=false;//未求得到各顶点的最短路径
			D[v]=G.edge[v0][v];
			P[v]=0;//初始化路径数组为0
		}
		//v0到v0的最短路径为0；已求得最短路径。
		D[v0]=0;
		finall[v0]=true;
		
		//主循环：求得v0到某个顶点v的最短路径
		for(v=1;v<G.VERTEXNUM;v++) {
			min=65535;
			//寻找距离v0最近的、未求得最短路径的顶点k
			for(w=0;w<G.VERTEXNUM;w++) {//
				if(!finall[w]&&D[w]<min) {
					k=w;
					min=D[w];
				}
			}

			finall[k]=true;//将k顶点标记为已求得最短路径
			
			//在v0到k的最短路径之上，对v相邻结顶点w的边进行计算，得到v0到这些相邻结点w的最短距离。
			for(w=0;w<G.VERTEXNUM;w++) {
				if(!finall[w]&&(min+G.edge[k][w]<D[w])){
					D[w]=min+G.edge[k][w];//不断更新v0到各个顶点的最短路径
					P[w]=k;
				}
			}
		}
	}
	
	public static void main(String[] arg) {
		int I =65535;
		int vertexnum=5;
		int sourceVertex=0;
		int[][] edge=new int[vertexnum][vertexnum];
		edge[0]= new int []{0,10,I,I,5};
		edge[1]= new int []{I,0,1,I,2};
		edge[2]= new int []{I,I,0,4,I};
		edge[3]= new int []{7,I,6,0,I};
		edge[4]= new int []{I,3,9,2,0};
		MGraph G=new MGraph(vertexnum,edge);
		int[] pathMatrix=new int[vertexnum];
		int[] shortPathTable=new int[vertexnum];
		shortestPathDij(G,sourceVertex,pathMatrix,shortPathTable);
		for(int i:shortPathTable) {
			System.out.print(i+" ");
		}
	}
	
}

