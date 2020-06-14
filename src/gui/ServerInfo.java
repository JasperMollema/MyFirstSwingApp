package gui;

public class ServerInfo {
    private String name;
    private int id;
    private boolean checked;

    public ServerInfo(String name, int id, boolean checked) {
        this.name = name;
        this.id = id;
        this.checked = checked;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }
}
