public class EnterDailyWeight implements Actions {
    User user;

    public EnterDailyWeight(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.EnterDailyWeight();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {
        user.UndoDailyWeight();
    }
}
