package cn.edu.seu.itcompany.toutiao;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.jdt.internal.compiler.lookup.ProblemBinding;

public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int N=in.nextInt();
            int M=in.nextInt();
            int P=in.nextInt();
            
            Coder[] coders=new Coder[M];
            Task[] tasks=new Task[P];
            for(int i=0;i<P;i++) {
            	tasks[i].pm=in.nextInt();
            	tasks[i].submitTime=in.nextInt();
            	tasks[i].por=in.nextInt();
            	tasks[i].doTime=in.nextInt();
            }
            schedule(N,M,P,tasks,coders);
        }
        in.close();
    }

	
	private static void schedule(int n, int m, int p, Task[] tasks, Coder[] coders) {
		
		int currentTime=0;
		
		PriorityQueue<Task> queue=new PriorityQueue<>();
		for(Task task:tasks) {
			queue.add(task);
		}
		
		
			
	}


	private class Task implements Comparable<Task>{
		int por;
		int submitTime;
		int pm;
		int doTime;
		@Override
		public int compareTo(Task o) {
			// TODO Auto-generated method stub
			if(this.por<o.por)
				return 1;
			if(this.por>o.por)
				return -1;
			if(this.doTime<o.doTime)
				return 1;
			if(this.doTime>o.doTime)
				return -1;
			if(this.pm<o.pm)
				return 1;
			if(this.pm>o.pm)
				return -1;
			return 0;
		}
	}
	
	private class Coder{
		int idleTime;
	}
	
	
	
}
