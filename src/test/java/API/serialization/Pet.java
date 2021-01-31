package API.serialization;

public class Pet {

    private String name;
    private int age;
    private String status;
    private  int id;
    private String photoUrl;

    public Pet(String name, int age, String status){
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
       return name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
