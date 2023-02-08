public class AddDailyFood implements Actions{
    User user;

    public AddDailyFood(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
        user.addToDailyFood();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}