package week15;

import java.io.Serializable;

/**
 *
 * @author spong
 */
public class FileDummy implements Serializable {
    private String filename, data;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
