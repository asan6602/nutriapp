import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class RecipeTest {
    @Test
    public void testConstructor() {
        Ingredient testIngredient1 = new Ingredient("potato", 100.00f);
        Ingredient testIngredient2 = new Ingredient("carrot", 80.00f);
        Ingredient testIngredient3 = new Ingredient("greenbeans", 120.00f);
        ArrayList<Ingredient> testlist = new ArrayList<Ingredient>();
        testlist.add(testIngredient1);
        testlist.add(testIngredient2);
        testlist.add(testIngredient3);
        Recipe testRecipe = new Recipe("veggies", testlist, "Throw in pot. Cook. Serve.");
        assertEquals(testRecipe.getName(), "veggies");
        assertEquals(testRecipe.getCalories(), 300.00f);
        assertEquals(testRecipe.getInstructions(), "Throw in pot. Cook. Serve.");
        

    }
}
