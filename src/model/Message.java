package model;

public class Message {
    private String title;
    private String contents;

    public Message(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
