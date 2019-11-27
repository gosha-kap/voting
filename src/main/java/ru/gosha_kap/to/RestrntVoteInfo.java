package ru.gosha_kap.to;


public class RestrntVoteInfo {

    private int voted;
    private String restaurantName;
    private int restaurntID;
    private int menuID;

    public RestrntVoteInfo(int id, String restaurantName, int voted, int menuID) {
        this.restaurntID = id;
        this.restaurantName = restaurantName;
        this.voted = voted;
        this.menuID = menuID;
    }


    public int getRestaurntID() {
        return restaurntID;
    }

    public void setRestaurntID(int restaurntID) {
        this.restaurntID = restaurntID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getVoted() {
        return voted;
    }

    public void setVoted(int voted) {
        this.voted = voted;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    @Override
    public String toString() {
        return "{" +
                "voted=" + voted +
                ", restaurantName='" + restaurantName + '\'' +
                ", id=" + restaurntID +
                '}';
    }
}
