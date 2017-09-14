package cn.edu.seu.itcompany.dji;

import java.util.Scanner;

/**
 * @Author personajian
 * @Date 2017/9/11 9:50
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int len = in.nextInt();
            Car a = new Car(1, in.nextInt(), in.nextInt(), in.nextInt());
            Car b = new Car(2, in.nextInt(), in.nextInt(), in.nextInt());

            int count = solve(len, a, b);

            System.out.println(count);

        }
    }

    private static int solve(int len, Car a, Car b) {


        while (a.power != 0 && b.power != 0) {

            /*if (a.power == 0 && b.power == 0)//ab都静止就跳出
                break;*/

            if ((a.locate <= 0 || a.locate > len - 1) && (b.locate <= 0 || b.locate > len - 1))//ab都越界，就跳出
                break;

            if (Math.abs(a.locate - b.locate) == 1) {//两车相遇了

                if (a.direction + b.direction == 0) {//相向而行，就会碰撞
                    if (a.power != 0 && b.power != 0) {//ab校车都没有静止
                        a.direction = 0 - a.direction;
                        b.direction = 0 - b.direction;
                        a.locate += a.direction;
                        a.power--;
                        b.locate += b.direction;
                        b.power--;
                    } else if (a.power == 0 && b.power != 0) {//a车静止了，b车撞a
                        a.direction = b.direction;
                        b.power -= 2;
                        a.locate += a.direction;
                        b.locate += a.direction;
                    } else if (b.power == 0 && a.power != 0) {//b车静止了，a车撞b
                        b.direction = a.direction;
                        a.power -= 2;
                        a.locate += a.direction;
                        b.locate += b.direction;
                    } else//ab都静止就跳出
                        break;
                }
            } else if (a.power != 0) {//同一时刻，只让一辆有power的车运行。
                a.power--;
                a.locate += a.direction;
            } else if (b.power != 0) {
                b.power--;
                b.locate += b.direction;
            }

        }

        int count = 0;

        if (a.locate <= 0 || a.locate > len - 1)
            count++;
        if (b.locate <= 0 || b.locate > len - 1)
            count++;

        return count;

    }


    private static class Car {
        int no;
        int power;
        int locate;
        int direction;

        public Car(int no, int power, int locate, int direction) {
            this.no = no;
            this.power = power;
            this.locate = locate;
            this.direction = direction;
        }

    }

}
