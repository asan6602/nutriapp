public class Undo implements Actions{
    User user;
    public Undo(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() {
        user.Undo();
        
    }

    @Override
    public void undoAction() {

    }
}
