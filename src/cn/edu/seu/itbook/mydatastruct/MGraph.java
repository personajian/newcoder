package cn.edu.seu.itbook.mydatastruct;

/**
 * @author personajian
 * 邻接矩阵
 */
public class MGraph{
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
