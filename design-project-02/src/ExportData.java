import java.io.IOException;

public class ExportData implements Actions{
    User user;

    public ExportData(User user) {
        this.user = user;
    }

    @Override
    public void executeAction() throws IOException {
        user.ExportData();
    }

    @Override
    public void undoAction() {

    }
}
