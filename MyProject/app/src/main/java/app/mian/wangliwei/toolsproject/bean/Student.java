package app.mian.wangliwei.toolsproject.bean;


public class Student {
    private int photoId;
    private String name;
    private int math;
    private int chinese;
    private int english;
    private int total;

    public Student(int photoId,String name,int math,int chinese,int english) {
        this.photoId = photoId;
        this.name = name;
        this.math = math;
        this.chinese = chinese;
        this.english = english;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getMath() {
        return math;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getChinese() {
        return chinese;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getEnglish() {
        return english;
    }

    public int getTotal() {
        total = math + chinese +english;
        return total;
    }
}
