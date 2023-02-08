public class SendInvite implements Actions{
    User user;

    public SendInvite(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.SendInvite();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {
        user.undoInvite();
    }
}
