package pe.edu.vallegrande.msvstudents.domain.enums;

public enum DocumentType {
    DNI("DNI"),
    PASSPORT("PASAPORTE"),
    FOREIGN_CARD("CARNET DE EXTRANJERIA"),
    OTHERS("OTROS");

    private final String value;

    DocumentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
} 