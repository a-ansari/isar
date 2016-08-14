package ir.isar.isarapp.test;

import org.junit.Test;

/**
 * Created by User on 14/03/2016.
 */
public class AppTest {

    @Test
    public void test() {
        for (char ch : convertYeAndKe("ی ي ک ك").toCharArray()) {
            System.out.println((int) ch);
        }
    }

    public String convertYeAndKe(String txt) {
        return txt.replaceAll("\u0643", "\u06A9").replaceAll("\u064A", "\u06CC");
    }

}
