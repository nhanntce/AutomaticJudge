package obj;

import java.util.HashMap;

/**
 *
 * @author NhanNT
 */
public class StudentResult {
    private HashMap<String, String> format;
    private HashMap<String, String> comment;
    private HashMap<String, String> plagiarsm;

    public StudentResult(HashMap<String, String> format, HashMap<String, String> comment, HashMap<String, String> plagiarsm) {
        this.format = format;
        this.comment = comment;
        this.plagiarsm = plagiarsm;
    }

    public HashMap<String, String> getFormat() {
        return format;
    }

    public void setFormat(HashMap<String, String> format) {
        this.format = format;
    }

    public HashMap<String, String> getComment() {
        return comment;
    }

    public void setComment(HashMap<String, String> comment) {
        this.comment = comment;
    }

    public HashMap<String, String> getPlagiarsm() {
        return plagiarsm;
    }

    public void setPlagiarsm(HashMap<String, String> plagiarsm) {
        this.plagiarsm = plagiarsm;
    }
    
    
}
