public class LogEntry{

    public int duration;
    public String streetName;

    public LogEntry(){
        duration = 0;
        streetName = "";
    }

    public LogEntry(int dur, String name) {
        duration = dur;
        streetName = name;
    }
}