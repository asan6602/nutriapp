import java.io.IOException;

public class ImportData implements Actions {
    User user;

    public ImportData(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() throws IOException {
        user.ImportData();
        
    }

    @Override
    public void undoAction() {

    }
}
