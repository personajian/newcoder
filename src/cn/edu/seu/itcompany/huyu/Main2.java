package cn.edu.seu.itcompany.huyu;

import java.util.HashMap;
import java.util.Scanner;


public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(in.nextInt(), in.nextInt());
                /*points[i].x=in.nextInt();
                points[i].y=in.nextInt();*/
            }
            int maxPoints = maxPoints(points, n);
            System.out.println(maxPoints);
        }
        in.close();
    }

    public static int maxPoints(Point[] points, int n) {
        if (points == null || n == 0) return 0;
        else if (n <= 2) return n;
        else {
            int maxPoints = 0;
            int x, y, point, count;
            HashMap<Double, Integer> map;
            for (int i = 0; i < n; i++) {
                x = points[i].x;
                y = points[i].y;
                point = 0;
                count = 1;
                map = new HashMap<>();
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        if (points[j].x == points[i].x && points[j].y == points[i].y) point++;
                        if (points[j].x == points[i].x) {
                            count++;
                            continue;
                        }
                        double div = (double) (points[j].y - points[i].y) / (points[j].x - points[i].x);
                        if (map.containsKey(div))
                            map.put(div, map.get(div) + 1);
                        else
                            map.put(div, 2);
                        maxPoints = Math.max(maxPoints, map.get(div) + point);
                    }
                }
                maxPoints = Math.max(count, maxPoints);
            }

            return maxPoints;
        }

    }


    private static class Point {
        int x;
        int y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}