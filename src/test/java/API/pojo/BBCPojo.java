package API.pojo;

import java.util.List;

public class BBCPojo {

    private int char_id;
    private String name;
    private String birthday;
    private List<String> occupation;
    private String status;
    private String nickname;
    private String img;
    private String portrayed;
    private String category;
    private List<String> better_call_saul_appearance;
    private List<String> appearance;

    public List<String> getAppearance() {
        return appearance;
    }

    public void setAppearance(List<String> appearance) {
        this.appearance = appearance;
    }

    public int getChar_id() {
        return char_id;
    }

    public void setChar_id(int char_id) {
        this.char_id = char_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<String> getOccupation() {
        return occupation;
    }

    public void setOccupation(List<String> occupation) {
        this.occupation = occupation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPortrayed() {
        return portrayed;
    }

    public void setPortrayed(String portrayed) {
        this.portrayed = portrayed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getBetter_call_saul_appearance() {
        return better_call_saul_appearance;
    }

    public void setBetter_call_saul_appearance(List<String> better_call_saul_appearance) {
        this.better_call_saul_appearance = better_call_saul_appearance;
    }
}
