import java.io.*;
public class Program {
    public static void main(String[] args) {

        try(FileInputStream fin=new FileInputStream("Example/img.png");
            FileOutputStream fos=new FileOutputStream("Example/newimg.png"))
        {
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer, 0, buffer.length);
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
