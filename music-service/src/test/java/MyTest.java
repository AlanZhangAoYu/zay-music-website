import org.junit.Test;
import util.*;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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
            File file=new File("F:\\音乐\\Sheep-张艺兴&Alan Walker.mp3");
            HashMap<String,String> map= Mp3Util.getMp3Info(file);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void VerificationCodeTest(){
        System.out.println(VerificationCodeUtil.generateVerificationCode());
    }
    @Test
    public void SnowflakeIdTest(){
        SnowflakeIdUtil idWorker = new SnowflakeIdUtil(0, 0);
        Runnable runnable= () -> {
            while (true){
                long nextId =idWorker.nextId();
                //注!!! 这句输出严重影响性能,在以后的开发中尽量少用
                //System.out.println(nextId);
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
