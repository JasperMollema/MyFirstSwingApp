package gui;

public class ServerInfo {
    private String name;
    private Integer id;
    private boolean checked;

    public static final Integer AMSTERDAM = 0;
    public static final String AMSTERDAM_EXT_ID = "Amsterdam";

    public static final Integer ALKMAAR = 1;
    public static final String ALKMAAR_EXT_ID = "Alkmaar";

    public static final Integer DEN_BOSCH = 2;
    public static final String DEN_BOSCH_EXT_ID = "Den Bosch";

    public static final Integer LONDON = 3;
    public static final String LONDON_EXT_ID = "London";

    public static final Integer BRISTOL = 4;
    public static final String BRISTOL_EXT_ID = "Bristol";

    public static final Integer LEEDS = 5;
    public static final String LEEDS_EXT_ID = "Leeds";

    public ServerInfo(Integer id) {
        this.id = id;

        switch (id) {
            case 0 : name = AMSTERDAM_EXT_ID;
                break;
            case 1 : name = ALKMAAR_EXT_ID;
                break;
            case 2 : name = DEN_BOSCH_EXT_ID;
                break;
            case 3 : name = LONDON_EXT_ID;
                break;
            case 4 : name = BRISTOL_EXT_ID;
                break;
            case 5 : name = LEEDS_EXT_ID;
                break;
        }
    }

    public void update(boolean isSelected) {
        checked = isSelected;
    }
    @Override
    public String toString() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getId() {
        return id;
    }
}
