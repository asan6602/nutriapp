import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class IngredientTest {
    @Test
    public void testConstructor() {
        Ingredient test1 = new Ingredient("potato", 100.00f);
        assertEquals(test1.getName(), "potato");
        assertEquals(test1.getCalories(), 100.00f);
        assertEquals(test1.getStock(), 0);
    }

    @Test
    public void stockOperations(){
        Ingredient test1 = new Ingredient("potato", 100.00f);
        test1.addStock();
        assertEquals(test1.getStock(), 1);
        test1.addStock(3);
        assertEquals(test1.getStock(), 4);
        test1.removeStock();
        assertEquals(test1.getStock(), 3);
        test1.removeStock(2);
        assertEquals(test1.getStock(), 1);

    }
}
