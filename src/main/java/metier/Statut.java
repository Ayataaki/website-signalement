package metier;

public enum Statut {
    NEW("new"),
    PROCESSING("processing"),
    FINAL("final");

    private final String label;

    Statut(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Statut fromLabel(String label) {
        for (Statut s : values()) {
            if (s.getLabel().equalsIgnoreCase(label)) return s;
        }
        throw new IllegalArgumentException("Statut inconnu : " + label);
    }
}
