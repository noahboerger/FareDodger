package base;

public enum Zone {
    INNENRAUM(1), AUSSENRAUM(2), XXL(3);

    private int validZone;

    Zone(int validZone) {
        this.validZone = validZone;
    }

    public static Zone fromString(String text) {
        for (Zone zone : Zone.values()) {
            if (zone.toString().equalsIgnoreCase(text)) {
                return zone;
            }
        }
        return null;
    }

    public boolean validIn(Zone zone) {
        if (validZone >= zone.validZone) {
            return true;
        } else {
            return false;
        }
    }
}
