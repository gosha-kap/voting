package ru.gosha_kap.to;


import java.util.Objects;

public class RestrntVoteInfo {

    private int voted;
    private String restaurantName;
    private int restaurnt_id;
    private int menu_id;

    public RestrntVoteInfo(int id, String restaurantName, int voted, int menu_id) {
        this.restaurnt_id = id;
        this.restaurantName = restaurantName;
        this.voted = voted;
        this.menu_id = menu_id;
    }

    public RestrntVoteInfo() {
    }

    public int getRestaurnt_id() {
        return restaurnt_id;
    }

    public void setRestaurnt_id(int restaurnt_id) {
        this.restaurnt_id = restaurnt_id;
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

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestrntVoteInfo that = (RestrntVoteInfo) o;
        return voted == that.voted &&
                restaurnt_id == that.restaurnt_id &&
                menu_id == that.menu_id &&
                Objects.equals(restaurantName, that.restaurantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voted, restaurantName, restaurnt_id, menu_id);
    }


}
