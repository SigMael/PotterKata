import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PotterBooksPriceShould {

    private Potter potter;

    @Before
    public void initTest(){
        potter = new Potter();
    }

    @Test
    public void return_0_when_no_book_bought(){
        Assert.assertEquals(0, potter.processPrice(new double[] {}), 0);
    }

    @Test
    public void return_8_when_one_book_is_bought(){
        Assert.assertEquals(8, potter.processPrice(new double[] {1}), 0);
    }

    @Test
    public void return_expected_reduction_when_two_books_are_bought(){
        Assert.assertEquals(8 * 2 * 0.95, potter.processPrice(new double[] {2, 1}), 0);
    }

    @Test
    public void return_no_reduction_when_two_identical_books_are_bought(){
        Assert.assertEquals(8 * 2 , potter.processPrice(new double[] {2, 2}), 0);
    }

    @Test
    public void return_expected_when_three_different_books_are_bought(){
        Assert.assertEquals(8 * 3 * 0.9 , potter.processPrice(new double[] {1, 2, 3}), 0);
    }

    @Test
    public void return_no_reduction_when_three_identical_books_are_bought(){
        Assert.assertEquals(8 * 3 , potter.processPrice(new double[] {2, 2, 2}), 0);
    }

    @Test
    public void return_lesser_reduction_when_2_identical_books_and_1_different_are_bought(){
        Assert.assertEquals(8 * 2 * 0.95 + 8 , potter.processPrice(new double[] {2, 2, 1}), 0);
    }
}
