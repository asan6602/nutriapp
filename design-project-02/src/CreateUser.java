public class CreateUser implements Actions{
    User user;

    public CreateUser(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
        user.CreateUser();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}
