package chandu.studentapp;

/**
 * Created by Chandu on 1/17/2018.
 */

public class Data {

    String name;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Data(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
