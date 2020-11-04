package edu.ucucite.teslalogin;

public class UserObj {
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String gender;
    private String status;

    public UserObj(String username, String password, String fullname, String address, String gender, String status) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.gender = gender;
        this.status = status;
    }

    public String print_user_info() {
        return "Username : " + username + '\n' +
                "Password : " + password + '\n' +
                "Full name : " + fullname + '\n' +
                "Address : " + address + '\n' +
                "Gender : " + gender + '\n' +
                "Status : " + status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
