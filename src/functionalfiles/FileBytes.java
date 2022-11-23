package functionalfiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class FileBytes {

    public static byte[] getBytesFromFile(String path)
    {
        byte[] buffer = null;
        try(FileInputStream fin=new FileInputStream(path))
        {
            buffer = new byte[fin.available()];
            fin.read(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return buffer;
    }

    public static void getFileFromBytes(String path, byte[] buffer)
    {
        try(FileOutputStream fos=new FileOutputStream(path))
        {
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public static byte[] longToBytes(List<Long> E) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * E.size());
        for(Long a: E) {
            buffer.putLong(a);
        }
        return buffer.array();
    }

    public static List<Long> bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        List<Long> E = new ArrayList<>();
        for(int i = 0; i < bytes.length / Long.BYTES; i++)
        {
            E.add(buffer.getLong());
        }
        return E;
    }
}
