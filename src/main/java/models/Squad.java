package models;

public class Squad {
    private int id;
    private String squadName;
    private String squadMission;
    private int squadSize;

    public Squad(String squadName, String squadCause, int squadSize) {
        this.squadName = squadName;
        this.squadMission = squadCause;
        this.squadSize = squadSize;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getSquadName() {

        return squadName;
    }

    public void setSquadName(String squadName) {

        this.squadName = squadName;
    }

    public String getSquadMission() {

        return squadMission;
    }

    public void setSquadMission(String squadCause) {

        this.squadMission = squadMission;
    }

    public int getSquadSize() {

        return squadSize;
    }

    public void setSquadSize(int squadSize) {

        this.squadSize = squadSize;
    }
}
