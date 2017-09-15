package cn.edu.seu.itcompany.hikvision;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {

	   /*Date currentTime = new Date();

	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	   System.out.println(dateString);*/
		Scanner in=new Scanner(System.in);
		while(in.hasNext()) {
			String str=in.nextLine();
			String[] input=str.split(",");
			int N=Integer.parseInt(input[0]);
			String startTime=input[1];
			String endTime=input[2];
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date begin = null;
			Date end=null;
			try {
				begin=df.parse(startTime);
				end = df.parse(endTime);
			} catch (ParseException e) {
				System.out.println("incorrect data");
			}
			
			int differ=(int)(end.getTime()-begin.getTime())/(1000*60);//除以1000*60是为了转换成分钟

			int remain=differ%15;
			int multi=differ/15;
			
			int circle=multi/N;
			int station=(multi+1)%N;
			
			if(multi%N!=0)
				circle++;
			
			System.out.print(circle+";");
			
			if(remain<=5)
				System.out.print(station+"-"+station);
			else {
				int nextStation=station+1;
				if(nextStation>=N) 
					nextStation=1;
				if(station==0)
					station=1;
				System.out.println(station+"-"+nextStation);
			}
				
			

			;
		}
		
	}

}
