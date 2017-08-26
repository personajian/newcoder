package cn.edu.seu.itcompany.didi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**https://www.nowcoder.com/questionTerminal/571cfbe764824f03b5c0bfd2eb0a8ddf
 * 小青蛙有一天不小心落入了一个地下迷宫,小青蛙希望用自己仅剩的体力值P跳出这个地下迷宫。
 * 为了让问题简单,假设这是一个n*m的格子迷宫,
 * 迷宫每个位置为0或者1,
 * 0代表这个位置有障碍物,小青蛙达到不了这个位置;
 * 1代表小青蛙可以达到的位置。
 * 小青蛙初始在(0,0)位置,地下迷宫的出口在(0,m-1)
 * (保证这两个位置都是1,并且保证一定有起点到终点可达的路径),
 * 小青蛙在迷宫中水平移动一个单位距离需要消耗1点体力值,
 * 向上爬一个单位距离需要消耗3个单位的体力值,
 * 向下移动不消耗体力值,
 * 当小青蛙的体力值等于0的时候还没有到达出口,小青蛙将无法逃离迷宫。
 * 现在需要你帮助小青蛙计算出能否用仅剩的体力值跳出迷宫(即达到(0,m-1)位置)。
 * @Author personajian
 * @Date 2017/8/26 10:45
 */
public class UndergroundMaze {

    private static int n,m,maxRemainEnery=0;
    private static int[][] map;
    private static boolean flag=false;
    private static String path="";
    private static LinkedList<String> queue=new LinkedList<>();

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
            n=in.nextInt();
            m=in.nextInt();
            int p=in.nextInt();

            map=new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j]=in.nextInt();
                }
            }

            isEscape(0,0,p);

            if(!flag)
                System.out.println("Can not escape!");
            else
                System.out.println(path);

        }
        in.close();
    }

    private static void isEscape(int x,int y,int p){
        //越界，不可达位置，直接返回
        if(p<0||x<0||y<0||x>=n||y>=n||map[x][y]!=1)
            return;
        //可达位置
        else{
            //可达位置入队列
            queue.offer("["+x+","+y+"]");
            map[x][y]=0;

            if(x==0&&y==m-1){//到达终点，将路径更新保存下来！
                if(p>=maxRemainEnery){
                    maxRemainEnery=p;
                    updatePath();
                }
                //继续递归，剔除队列
                map[x][y]=1;
                queue.removeLast();

                flag=true;
                return;
            }
            //四个方向移动
            isEscape(x,y+1,p-1);
            isEscape(x+1,y,p);
            isEscape(x-1,y,p-3);
            isEscape(x,y-1,p-1);

            //对于不满足的点或子递归结束的点 剔除队列
            map[x][y]=1;
            queue.removeLast();
        }
    }

    private static void updatePath(){
        StringBuilder sb=new StringBuilder();
        Iterator<String> it=queue.iterator();
        while(it.hasNext()){
            sb.append(it.next()+",");
        }
        if(sb.length()>0)
            sb.deleteCharAt(sb.length()-1);
        path=sb.toString();
    }
}
