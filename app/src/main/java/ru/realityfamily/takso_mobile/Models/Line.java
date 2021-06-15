package ru.realityfamily.takso_mobile.Models;

public class Line {
    private static Line Instance;

    public static Line getInstance() {
        if (Instance == null) {
            Instance = new Line();
        }
        return Instance;
    }
    private Line() {
    }

    boolean onLine;

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }
}
