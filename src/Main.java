
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    /**
     * Java NIO 是通道和缓存进行数据传输
     * */
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:\\software\\IntelliJ IDEA 2018.2.7\\bin\\idea.exe.vmoptions", "rw");
        FileChannel  channel =  file.getChannel();
        ByteBuffer  buffer = ByteBuffer.allocate(10);
        int read = channel.read(buffer);
        while (read != -1) {
            System.out.println("读取的字节数为:" + read);
            //从写模式转换为读模式
            buffer.flip();

            //判断缓冲区里面是否还有数据
            while (buffer.hasRemaining()) {
                System.out.println((char)buffer.get());
            }

            //清空缓冲区，设置成初始的状态(事实上buffer里面的数据还在，只是没有任何标记表示哪些数据被读过，哪些没有被读过)
            //compact()方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
            buffer.clear();
            read = channel.read(buffer);
        }
    }
}
