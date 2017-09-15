package cn.edu.seu.itcompany.neteasy1;

import java.util.*;

public class Main1 {
	
    public static void main(String[] args) {
    	
        Scanner in = new Scanner(System.in);
        
        while(in.hasNext()){
            String line = in.nextLine();
            List<String> list = permutation(line);
            list.remove(line);

            Map<Integer,Integer> map = new HashMap<>();
            for(String str : list){
                int num = commonSequences(line,str);
                if(!map.containsKey(num)){
                    map.put(num,1);
                }else{
                    map.put(num,map.get(num) + 1);
                }
            }
            int result = Integer.MIN_VALUE;
            for(Map.Entry<Integer,Integer> entry : map.entrySet()){
                if(entry.getValue() > result)
                    result = entry.getValue();
            }

            System.out.println(result);
        }
        in.close();
    }

    public static int commonSequences(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        if(A== null || B == null||lenA == 0|| lenB ==0)
            return 0;
        int d[][] = new int[lenA + 1][lenB + 1];
        for(int i = 1;i <= lenA;i++){
            for(int j = 1;j <= lenB;j++){
                char a = A.charAt(i-1);
                char b = B.charAt(j-1);
                if(a ==b ){
                    d[i][j] = d[i-1][j-1] + 1;
                }else{
                    d[i][j] = Math.max(d[i-1][j],d[i][j-1]);
                }
            }
        }
        return d[lenA][lenB];
    }



    public static ArrayList<String> permutation(String str){
        char[] array = str.toCharArray();
        ArrayList<String> result = new ArrayList<>();
        permutationHelper(array,0,result);
        Collections.sort(result);
        return result;
    }

    private static void permutationHelper(char[] array, int i, List<String> result) {
        if(i == array.length - 1){
            String value = String.valueOf(array);
            if(!result.contains(value)) {
                if(isLegal(value)) {
                    result.add(value);
                }
            }
        }else{
            for(int j = i;j < array.length;j++){
                swap(array,i,j);
                permutationHelper(array,i + 1,result);
                swap(array,i,j);
            }
        }
    }

    public static boolean isLegal(String line){
        char[] tags = line.toCharArray();
        int leftNum = 0;
        for(int i = 0;i < tags.length;i++){
            if('(' == tags[i]){
                leftNum++;
            }else if(')' == tags[i]){
                leftNum--;
            }
            if(leftNum < 0)
                return false;
        }
        if(0 == leftNum)
            return true;
        return false;
    }

    private static void swap(char[] array, int item, int i) {
        char temp = array[item];
        array[item] = array[i];
        array[i] = temp;
    }
}
