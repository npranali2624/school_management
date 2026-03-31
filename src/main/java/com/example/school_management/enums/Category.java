package com.example.school_management.enums;

public enum Category {

    GENERAL("General"),
    OBC("Other Backward Class (OBC)"),
    OBC_A("OBC-A"),
    OBC_B("OBC-B"),
    SC("Scheduled Caste (SC)"),
    ST("Scheduled Tribe (ST)"),
    NT("Nomadic Tribe (NT)"),
    NT_A("NT-A"),
    NT_B("NT-B"),
    NT_C("NT-C"),
    NT_D("NT-D"),
    SBC("Special Backward Class (SBC)"),
    VJNT("Vimukta Jati & Nomadic Tribes (VJNT)"),
    EWS("Economically Weaker Section (EWS)"),
    OTHER("Other");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

