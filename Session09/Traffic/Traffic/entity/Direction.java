package JavaAdvanced.Ss9.Traffic.entity;

public enum Direction {
    NORTH("Bắc"),
    SOUTH("Nam"),
    EAST("Đông"),
    WEST("Tây");

    private String vietnamese;

    Direction(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getVietnamese() {
        return vietnamese;
    }
}