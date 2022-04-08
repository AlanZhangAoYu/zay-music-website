import org.junit.Test;
import util.Md5Util;
import util.Mp3Util;
import util.RandomSaltUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class MyTest {
    Scanner scanner= new Scanner(System.in);
    @Test
    public void Md5Test(){
        System.out.println(Md5Util.getMd5Str("QianNvYouHun"));
    }
    @Test
    public void RandomSaltTest(){
        System.out.println(RandomSaltUtil.generateRandomCode());
    }
    @Test
    public void Mp3Test(){
        try {
            File file=new File("F:\\音乐\\G.E.M.邓紫棋 - 龙卷风.mp3");
            HashMap<String,String> map= Mp3Util.getMp3Info(file);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
