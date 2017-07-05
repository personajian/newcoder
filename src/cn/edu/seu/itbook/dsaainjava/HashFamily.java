package cn.edu.seu.itbook.dsaainjava;

public interface HashFamily<AnyType>
{
    int hash( AnyType x, int which );
    int getNumberOfFunctions( );
    void generateNewFunctions( );
}
