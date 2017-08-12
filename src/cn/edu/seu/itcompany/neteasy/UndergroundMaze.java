package cn.edu.seu.itcompany.neteasy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**青蛙跳出地下迷宫
 * https://www.nowcoder.com/questionTerminal/571cfbe764824f03b5c0bfd2eb0a8ddf
 * @Author personajian
 * @Date 2017/8/11 9:11
 */
public class UndergroundMaze {
    private static int startx,starty,endx,endy,n,m,a[][],book[][],p; //分别为起点的坐标和出口的坐标,行和列
    private static int min=Integer.MAX_VALUE;//体力值,默认最大
    private static LinkedList<Point> linkedList = new LinkedList<>();
    private static int next[][]=new int[][]{{0,1},{1,0},{0,-1},{-1,0}};//分别为右，下，左，上移动时需要加的坐标
    private static String path = "";
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();//n行
        m=sc.nextInt();//m列
        p=sc.nextInt();//体力
        startx=0;starty=0;
        endx=0;endy=m-1;
        a=new int[n][m];
        book=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                a[i][j]=sc.nextInt();
            }
        }//输入行和列
        dfs(0, 0, 0);
        if(min==Integer.MAX_VALUE){
            System.out.println("Can not escape!");
        }else {
            System.out.println(path.substring(0,path.length()-1));
        }

    }
    public static void dfs(int curx,int cury,int curt){
        linkedList.add(new Point(curx, cury));
        if(curt>p){
            return;
        }
        if(curx==endx&&cury==endy&&curt<min){
            min=curt;
            savePath();
            return;
        }
        int tx=0;
        int ty=0;
        for(int i=0;i<=3;i++){//遍历四个方向,规定顺时针遍历
            tx=curx+next[i][0];
            ty=cury+next[i][1];
            if(tx<0||tx>=n||ty<0||ty>=m){
                continue;
            }
            if(a[tx][ty]==1&&book[tx][ty]==0){
                book[tx][ty]=1;
                switch (i) {
                    case 0:
                        curt+=1;
                        break;
                    case 1:
                        break;
                    case 2:
                        curt+=1;
                    case 3:
                        curt+=3;
                        break;
                    default:
                        break;
                }
                dfs(tx, ty, curt);
                linkedList.removeLast();
                book[tx][ty]=0;
            }

        }
    }

    private static class Point{
        int x, y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }//点类

    private static void savePath() {
        Iterator<Point> iterator = linkedList.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Point point = iterator.next();
            sb.append("[").append(point.x).append(",").append(point.y).append("],");
        }
        path = sb.toString();

    }
}
