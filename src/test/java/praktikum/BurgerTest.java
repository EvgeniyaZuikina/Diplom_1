package praktikum;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
       private Burger burger;

    @Before
    public void startUp() {
        burger = new Burger();
    }

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Mock
    private List<Bun> buns = new ArrayList<>();

    @Mock
    private List<Ingredient> ingredients = new ArrayList<>();

    @Mock
    private Database mockDatabase;

    @Test
    public void addIngredientTest(){
        burger.addIngredient(mockIngredient);
        int expectedSize = 1;
        int actualSize = burger.ingredients.size();
        assertEquals("Amount of ingredients is not equivalent",expectedSize, actualSize);

    }

    @Test
    public void removeIngredientTest(){
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        int expectedSize = 0;
        int actualSize = burger.ingredients.size();
        assertEquals("Ingredient is not removed", expectedSize, actualSize);
    }

    @Test
    public void moveIngredientTest(){
        burger.addIngredient(mockIngredient);
        int currentIndex = burger.ingredients.indexOf(mockIngredient);
        int newIndex = 0;
        burger.moveIngredient(currentIndex, newIndex);
        assertEquals("Ingredient is not moved", burger.ingredients.size(), 1);
        assertEquals(burger.ingredients.indexOf(mockIngredient), newIndex);
    }

    @Test
    public void getPriceTest(){
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        when(mockBun.getPrice()).thenReturn(200f);
        when(mockIngredient.getPrice()).thenReturn(200f);
        float expectedPrice = 200 * 2 + 200;
        float actualPrice = burger.getPrice();
        assertEquals("Burger's price calculated incorrectly",expectedPrice, actualPrice, 0);
    }

    @Test
    public void getReceiptTest(){
        burger.setBuns(mockBun);
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        burger.addIngredient(mockIngredient);
        when(mockIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient.getName()).thenReturn("dinosaur");
        when(mockIngredient.getPrice()).thenReturn(300f);
        String expectedReceipt = "(==== black bun ====)\r\n= filling dinosaur =\r\n(==== black bun ====)\r\n\r\nPrice: 500,000000\r\n";
        String actualReceipt = burger.getReceipt();
        assertEquals("Receipt contains invalid data", expectedReceipt, actualReceipt);

    }

    @Test
    public void databaseTest() {
        buns.add(new Bun("black bun", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        assertNotNull("Buns is not available", mockDatabase.availableBuns());
        assertNotNull("Ingredients is not available", mockDatabase.availableIngredients());
    }
}
