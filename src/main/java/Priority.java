public enum Priority {

    DEFAULT("default"),
    NORMAL("normal"),
    HIGH("high");

    private String value;

    Priority(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static  Priority fromString(String s) {
        for (Priority p : Priority.values()) {
            if (p.value.equalsIgnoreCase(s)) {
                return p;
            }
        }
        return null;
    }
}
