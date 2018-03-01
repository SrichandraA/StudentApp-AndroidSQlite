package chandu.studentapp;

/**
 * Created by Chandu on 1/18/2018.
 */

public class StudentData {
    String name,email;
    int id,pid;

    public StudentData(String name, String email, int id, int pid) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
