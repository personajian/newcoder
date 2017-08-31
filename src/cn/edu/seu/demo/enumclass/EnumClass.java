package cn.edu.seu.demo.enumclass;

/**
 * @Author personajian
 * @Date 2017/8/30 16:07
 */
public class EnumClass {

    private enum Season {
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER
    }

    public static void main(String[] args) {
        //Enum.values()
        Season[] seasons=Season.values();
        for(Season s:seasons){
            System.out.println(s.name());
            System.out.println(s.ordinal());
        }

    }
}
