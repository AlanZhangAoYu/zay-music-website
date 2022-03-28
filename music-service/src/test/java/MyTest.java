import org.junit.Test;
import util.Md5Util;
import util.RandomSaltUtil;

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
}
