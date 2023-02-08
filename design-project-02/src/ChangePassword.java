public class ChangePassword implements Actions {
    User user;

    public ChangePassword(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.ChangePassword();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
