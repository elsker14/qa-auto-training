package seleniumTraining.section9sync.assignment;

public class CustomPair {
    private String key;
    private String value;

    public CustomPair() {
        this.key = "";
        this.value = "";
    }

    public CustomPair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
