import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class TestFile {
    public static void main(String[] args){
        File file=new File("Example/img.png");
        byte[] b= getBytesFromFile(file);
        getFileFromBytes(b,"Example/image.png");
    }

    public static byte[] getBytesFromFile(File file){
        byte[] ret=null;
        try{
            if (file==null){
                // log.error("helper:the file is null!");
                return null;
            }
            FileInputStream in=new FileInputStream(file);
            ByteArrayOutputStream out=new ByteArrayOutputStream(4096);
            byte[] b=new byte[4096];
            int n;
            while ((n= in.read(b))!=-1){
                out.write(b,0, n);
            }
            in.close();
            out.close();
            ret= out.toByteArray();
        }catch (IOException e){
            // log.error("helper:get bytes from file process error!");
            e.printStackTrace();
        }
        return ret;
    }
    public static File getFileFromBytes(byte[] b, String outputFile){
        File ret=null;
        BufferedOutputStream stream=null;
        try{
            ret=new File(outputFile);
            FileOutputStream fstream= new FileOutputStream(ret);
            stream=new BufferedOutputStream(fstream);
            stream.write(b);
        }catch (Exception e){
            // log.error("helper:get file from byte process error!");
            e.printStackTrace();
        }finally{
            if (stream!=null){
                try{
                    stream.close();
                }catch (IOException e){
                    // log.error("helper:get file from byte process error!");
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

}
