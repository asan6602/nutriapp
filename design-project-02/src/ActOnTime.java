import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;

public interface ActOnTime {

    /**
     * @throws IOException
     * @throws CsvValidationException
     *
     */
    public void dailyUpdate() throws CsvValidationException, IOException;
}
