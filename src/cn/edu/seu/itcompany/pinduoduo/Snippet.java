package cn.edu.seu.itcompany.pinduoduo;

import java.util.LinkedList;

public class Snippet {
	public static void Match02(String s){
			LinkedList<Character> stack=new LinkedList<>();
			boolean flag=true;
			char[] expression=s.toCharArray();
			int i=0;
			while(i<expression.length){
				while(expression[i]=='('||expression[i]=='['||expression[i]=='{'){
					stack.add(expression[i]);
					i++;
				}
				if(expression[i]!=')'&&expression[i]!=']'&&expression[i]!='}'){
					i++;
					continue;
				}
				if(expression[i]==')'||expression[i]==']'||expression[i]=='}'){
					char c='(';
					if(expression[i]==')'){
						c='(';
					}else if(expression[i]==']'){
						c='[';
					}else{
						c='{';
					}
					if(stack.isEmpty()||c!=stack.pollLast()){
						flag=false;
						break;
					}
				}
				i++;
			}
			if(!stack.isEmpty())
				flag=false;
			System.out.println(flag);
		}
	
}

