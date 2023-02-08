public class BrowseRecipe implements Actions {
    User user;

    public BrowseRecipe(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.BrowseRecipe();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
