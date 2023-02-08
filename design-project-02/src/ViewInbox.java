public class ViewInbox implements Actions{
    User user;

    public ViewInbox(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.ViewInbox();
        
    }

    @Override
    public void undoAction() {

    }
}
