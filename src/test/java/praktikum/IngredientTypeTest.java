package praktikum;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void getSauceTypeTest() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void getFillingTypeTest() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
