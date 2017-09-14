package cn.edu.seu.itcompany.dji;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            int len = sc.nextInt();

            int aPower = sc.nextInt();
            int aLoc = sc.nextInt();
            int aDire = sc.nextInt();

            int bPower = sc.nextInt();
            int bLoc = sc.nextInt();
            int bDire = sc.nextInt();

            int count = 0;
            //如果反向
            if (aDire + bDire == 0) {
                if (bPower + bLoc >= len || bLoc - bPower < 0) {
                    count++;
                }
                if (aPower + aLoc >= len || aLoc - aPower < 0) {
                    count++;
                }
            } else {
                //如果同向,a向右，b向左
                if (aDire == 1 && bDire == -1) {
                    while (bLoc - aLoc > 2) {
                        bPower--;
                        aPower--;
                        aLoc++;
                        bLoc--;
                        if (bPower == 0 || aPower == 0) {
                            break;
                        }
                    }
                    //b向左为0，a向右不为0
                    if (bPower == 0 && aPower != 0) {
                        //中间有空格
                        while (bLoc - aLoc > 1) {
                            aPower--;
                            aLoc++;
                            if (aPower == 0) {
                                break;
                            }
                        }
                        if (aPower == 0) {
                            break;
                        }
                        while (aPower > 0) {
                            if (aPower == 0) {
                                break;
                            }
                            aPower = aPower - 2;
                            bLoc++;
                            aLoc++;

                        }
                        if (bLoc >= len) {
                            count++;
                        }
                        if (aLoc >= len) {
                            count++;
                        }
                    } else if (bPower != 0 && aPower == 0) {
                        //中间有空格
                        while (bLoc - aLoc > 1) {
                            bPower--;
                            bLoc--;
                            if (bPower == 0) {
                                break;
                            }
                        }
                        if (bPower == 0) {
                            break;
                        }
                        while (bPower > 0) {
                            if (bPower == 0) {
                                break;
                            }
                            bPower = bPower - 2;
                            bLoc--;
                            aLoc--;
                        }
                        if (bLoc < 0) {
                            count++;
                        }
                        if (aLoc < 0) {
                            count++;
                        }
                    } else if (bPower != 0 && aPower != 0) {
                        if (bPower + bLoc >= len) {
                            count++;
                        }
                        if (aLoc - aPower < 0) {
                            count++;
                        }
                    }

                }
            }
            list.add(count);
//			System.out.println(count);
        }
        for (Integer i : list) {
            System.out.println(i);
        }
    }

}
