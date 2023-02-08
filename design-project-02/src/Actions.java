import java.io.IOException;

public interface Actions {
    /**
     *
     */
    public void executeAction() throws IOException;
    public void undoAction();
}
