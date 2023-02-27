import org.junit.Test;

import static org.junit.Assert.*;

public class InternationalTest {

    @Test
    public void isStudyAbroad1() {
        Profile profile1 = new Profile("Beezer", "Bozo", new Date("2/14/2000"));
        International international1 = new International(profile1, Major.ITI, 35, true);
        assertEquals(5918, international1.tuitionDue(3), 0);
    }

    @Test
    public void isStudyAbroad2() {
        Profile profile2 = new Profile("Duper", "Super", new Date("7/24/2001"));
        International international2 = new International(profile2, Major.CS, 67, true);
        assertEquals(5918, international2.tuitionDue(9), 0);
    }

    @Test
    public void isNotStudyAbroad1() {
        Profile profile3 = new Profile("Mia", "Mama", new Date("2/03/1998"));
        International international3 = new International(profile3, Major.BAIT, 43, false);
        assertEquals(40485, international3.tuitionDue(21), 0);
    }

    @Test
    public void isNotStudyAbroad2() {
        Profile profile4 = new Profile("Creeper", "Jeeper", new Date("1/03/1999"));
        International international4 = new International(profile4, Major.MATH, 100, false);
        assertEquals(35655, international4.tuitionDue(12), 0);
    }
}