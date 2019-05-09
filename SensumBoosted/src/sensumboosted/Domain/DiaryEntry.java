
package sensumboosted.Domain;

public class DiaryEntry {
    String text;
    long diaryEntryId;
    
    public DiaryEntry(String text, long diaryEntryId) {
        this.text = text;
        this.diaryEntryId = diaryEntryId;
    }
    
    public DiaryEntry(String text) {
        this.text = text;
    }
    
        public long getDiaryId() {
        return diaryEntryId;
    }

    public void setDiaryId(long diaryEntryId) {
        this.diaryEntryId = diaryEntryId;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

}
