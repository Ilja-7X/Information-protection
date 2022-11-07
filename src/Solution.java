import java.io.FileInputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;


public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("Example/file.txt");
        ArrayList<Integer> listData = new ArrayList<>();
        while (inputStream.available() > 0)
            listData.add(inputStream.read());
        inputStream.close();
        /*for(int i = 0; i < listData.size(); i++) {
            System.out.print(listData.get(i) + " ");
        }*/
        for(int i: listData) {
            System.out.print(listData.get(i) + " ");
        }
        System.out.println();
        System.out.println();
    }
}
