package model;

public class User {
    private Integer userId;
    private String firstName;
    private String lastName;

    private Boolean isActive;

    public User(){}
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getInfo(){
        return "FirstName: "+this.firstName+", LastName: "+this.lastName+", isActive: "+(this.isActive?"YES":"NO");
    }
}
