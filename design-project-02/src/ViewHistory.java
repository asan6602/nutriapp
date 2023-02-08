public class ViewHistory implements Actions{
    User user;

    public ViewHistory(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.ViewHistory();
    }

    @Override
    public void undoAction() {

    }
}
