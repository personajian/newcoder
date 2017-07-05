package cn.edu.seu.newcoder.itcompany.huawei;

/**
 * @author personajian
 * Dijkstra算法求单源最短路径。
 */
public class Fxthree {
	
	public static void shortestPathDij(MGraph G,int v0,int[] P,int[] D) {
		
		int v,w,k=0,min;
		
		boolean[] finall=new boolean[G.VERTEXNUM];
		for(v=0;v<G.VERTEXNUM;v++) {
			finall[v]=false;
			D[v]=G.edge[v0][v];
			P[v]=0;
		}
		D[v0]=0;
		finall[v0]=true;
		
		for(v=1;v<G.VERTEXNUM;v++) {
			min=65535;
			for(w=0;w<G.VERTEXNUM;w++) {
				if(!finall[w]&&D[w]<min) {
					k=w;
					min=D[w];
				}
			}
			finall[k]=true;
			for(w=0;w<G.VERTEXNUM;w++) {
				if(!finall[w]&&(min+G.edge[k][w]<D[w])){
					D[w]=min+G.edge[k][w];
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


class MGraph{
	int VERTEXNUM;
	int[] vertex;
	int[][] edge;
	public MGraph(int vertextnum) {
		VERTEXNUM=vertextnum;
		vertex=new int[vertextnum];
		edge=new int[vertextnum][vertextnum];
	}
	public MGraph(int vertextnum,int[][] edge) {
		this.VERTEXNUM=vertextnum;
		this.vertex=new int[vertextnum];
		this.edge=edge;
	}
}

