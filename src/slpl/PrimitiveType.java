package slpl;

public enum PrimitiveType {

    NUMBER("number"),
    BOOLEAN("boolean"),
    VOID("void"),
    NULL("null"),
    STRING("string");

    private final String name;

    PrimitiveType(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return name;
    }
}