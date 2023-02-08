public class ViewShoppingItem implements Actions{
    User user;

    public ViewShoppingItem(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
        user.ViewShoppingList();
    }

    @Override
    public void undoAction() {

    }
}
