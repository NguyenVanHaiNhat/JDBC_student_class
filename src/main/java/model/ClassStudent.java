package model;

public class ClassStudent {
    protected String idClass;
    protected String className;

    public ClassStudent(String id, String className) {
        this.idClass = id;
        this.className = className;
    }
    public ClassStudent(){}

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
