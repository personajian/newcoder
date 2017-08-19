package cn.edu.seu.swordoffer;

public class T11_Power {
    public double Power(double base, int exponent) {
        double multi=1;
        if(exponent==0)
            return 1;
        if(exponent<0){
            for (int i = 0; i < - exponent; i++) {
                multi*=base;
            }
            multi=1/multi;
        }else{
            for (int i = 0; i <  exponent; i++) {
                multi*=base;
            }
        }
        return multi;
  }
}