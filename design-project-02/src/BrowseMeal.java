public class BrowseMeal implements Actions {
    User user;

    public BrowseMeal(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.BrowseMeal();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
