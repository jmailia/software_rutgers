import org.junit.Test;
import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void test1(){
        Date test1 = new Date("4/20/2015");
        assertFalse(test1.isValid());
    }

    @Test
    public void test2(){
        Date test2 = new Date("10/7/1885");
        assertFalse(test2.isValid());
    }

    @Test
    public void test3(){
        Date test3 = new Date("13/17/2001");
        assertFalse(test3.isValid());
    }

    @Test
    public void test4(){
        Date test4 = new Date("3/32/2003");
        assertFalse(test4.isValid());
    }

    @Test
    public void test5(){
        Date test5 = new Date("4/31/2003");
        assertFalse(test5.isValid());
    }

    @Test
    public void test6(){
        Date test6 = new Date("8/31/1997");
        assertTrue(test6.isValid());
    }

    @Test
    public void test7(){
        Date test7 = new Date("2/29/2000");
        assertTrue(test7.isValid());
    }

}