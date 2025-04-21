package DTO;


public class userDTO {
    private int ID;
    private String UserName;
    private String PassWord;
    public userDTO(int ID,String UserName, String PassWord){
        this.ID = ID;
        this.UserName = UserName;
        this.PassWord = PassWord;
    }

    public userDTO(String userName, String passWord) {
        this.ID = 0;  // Gán ID mặc định là 0 (hoặc bất kỳ giá trị hợp lý nào)
        this.UserName = userName;
        this.PassWord = passWord;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getPassWord() {
        return PassWord;
    }
    public void setPassWord(String passWord) {
        PassWord = passWord;
    }



    
}
