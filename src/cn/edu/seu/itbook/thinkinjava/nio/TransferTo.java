package cn.edu.seu.itbook.thinkinjava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**《Java编程思想》Page552
 * NIO的新概念：通道和缓冲器，通道是运输工具，缓冲器是数据，没有直接与通道交互，只是和缓冲器交互。
 * FileChannel：通道能够从FileInputStream, FileOutputStream, RandomAccessFile的getChannel()中获得。
 * ByteBufferL：唯一直接与通道交互的缓冲器，存储未加工字节的缓冲器。低级但正好，这是大多数OS中更有效的映射方式。
 * @Author personajian
 * @Date 2017/8/14 20:33
 */
public class TransferTo {

    private static final int BSIZE=1024;

    private static String[] arg={"CopyChannel.java","test.txt"};

    public static void main(String[] args) throws IOException {
        FileChannel in=new FileInputStream(arg[0]).getChannel();
        FileChannel out=new FileOutputStream(arg[1]).getChannel();

        //效率底下
        /*ByteBuffer buffer= ByteBuffer.allocate(BSIZE);

        while(in.read(buffer)!=-1){
            //flip()为读取数据做好准备
            buffer.flip();
            //
            out.write(buffer);
            //clear()内部指针重排，为另一个read()周期做准备
            buffer.clear();
        }*/

        //通道之间直接交换数据
        in.transferTo(0,in.size(),out);
        //out.transferFrom(in,0,in.size());
    }
}
