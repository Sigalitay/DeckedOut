/* Simple utility class to retrieve a string letter by letter in same thread / context */
public class StringUtility {

    private String data;
    private boolean started;

    private long startMs;
    private int intervalMs;

    public StringUtility(String data, int ms) {
        this.data = data;
        this.intervalMs = ms;

        this.started = false;
    }

    public void reset() {
        this.started = false;
    }

    public String retrieve() {
        if (!this.started) {
            this.startMs = System.currentTimeMillis();
            this.started = true;
        }
        long delta = System.currentTimeMillis() - startMs;
        int slices = (int) (delta / intervalMs);

        int index = slices < data.length() ? slices : data.length();

        return data.substring(0, index);
    }
}

/*
StringUtility Update V3:
- Declare StringUtility in the instance variable.
- Timer will only start counting when you first call .retrieve()
- Reset utility with reset(), and timer will start again after the first retrieve() call
 */