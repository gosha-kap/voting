package ru.gosha_kap.to;


import java.util.Objects;

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

    public RestrntVoteInfo() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestrntVoteInfo that = (RestrntVoteInfo) o;
        return voted == that.voted &&
                restaurntID == that.restaurntID &&
                menuID == that.menuID &&
                Objects.equals(restaurantName, that.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voted, restaurantName, restaurntID, menuID);
    }

    @Override
    public String toString() {
        return "RestrntVoteInfo{" +
                "voted=" + voted +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
