package cn.edu.seu.itbook.thinkinjava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**《Java编程思想》Page552
 * NIO的新概念：通道和缓冲器，通道是运输工具，缓冲器是数据，没有直接与通道交互，只是和缓冲器交互。
 * FileChannel：通道能够从FileInputStream, FileOutputStream, RandomAccessFile的getChannel()中获得。
 * ByteBufferL：唯一直接与通道交互的缓冲器，存储未加工字节的缓冲器。低级但正好，这是大多数OS中更有效的映射方式。
 * @Author personajian
 * @Date 2017/8/14 20:33
 */
public class GetChannel {

    private static final int BSIZE=1024;

    public static void main(String[] args) throws IOException {
        FileChannel fc=new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();;

        fc=new RandomAccessFile("data.txt","rw").getChannel();
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();

        fc=new FileInputStream("data.txt").getChannel();
        ByteBuffer buff=ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        //filp()为读取字节做好准备
        buff.flip();
        while(buff.hasRemaining()){
            System.out.print((char)buff.get());
        }
    }
}
