
public class CreateRecipe implements Actions{
    User user;

    public CreateRecipe(User user) {
        this.user = user;
    }
    @Override
    public void executeAction() {
        user.CreateRecipe();
        CommandCollection.CommandLog.add(this);
    }

    @Override
    public void undoAction() {

    }
}    