
package sensumboosted.Domain;

public class LogEntry {
    String text;
    long logbookEntryId;
    
    public LogEntry(String text, long logbookEntryId) {
        this.text = text;
        this.logbookEntryId = logbookEntryId;
    }
    
    public LogEntry(String text) {
        this.text = text;
    }
    
        public long getLogbookId() {
        return logbookEntryId;
    }

    public void setLogbookId(long logbookEntryId) {
        this.logbookEntryId = logbookEntryId;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

}
