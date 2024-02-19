package model;

public class Student extends ClassStudent {
    protected int id;
    protected String name;
    protected String email;
    protected String address;
    protected String c_id;
    public Student() {
    }
    public Student(String name, String email, String address, String c_id) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.c_id = c_id;
    }

    public Student(int id, String name, String email, String address, String className) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
