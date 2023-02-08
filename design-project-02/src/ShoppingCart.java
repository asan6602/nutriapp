import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static List<Ingredient> ItemsToBuy = new ArrayList<>();

    public static void addItem(Ingredient Item){
        ItemsToBuy.add(Item);
        System.out.println("Ingredient " + Item.getName() + " was added to the Shopping List");
    }

    public static void removeItem(int Item){
        Ingredient reference = ItemsToBuy.remove(Item);
        System.out.println("Ingredient " + reference.getName() + " was removed from the Shopping List");
    }

    public static void showCart(){
        int itemIndicator = 1;
        System.out.println("---Your Shopping Cart---");
        for (Ingredient ingredient : ItemsToBuy) {
            System.out.println("Ingredient " + itemIndicator + ": " + ingredient.getName());
            itemIndicator++;
        }
    }
}
