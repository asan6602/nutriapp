public class BrowseIngredient implements Actions {
    User user;

    public BrowseIngredient(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.BrowseIngredient();
    }

    @Override
    public void undoAction() {

    }
}
