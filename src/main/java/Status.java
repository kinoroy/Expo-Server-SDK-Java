public enum Status {

    ERROR("error"),
    OK("ok");

    private String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static Status fromString(String s) {
        for (Status p : Status.values()) {
            if (p.value.equalsIgnoreCase(s)) {
                return p;
            }
        }
        return null;
    }
}

