public class LeaveTeam implements Actions {
    User user;

    public LeaveTeam(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.LeaveTeam();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
