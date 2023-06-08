/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


/**
 *
 * @author vsngh
 */
import entity.UserEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.UserRepository;

public class UserService {
    
    UserRepository userRepo = new UserRepository();
    
    public UserEntity checkEmailExist(String email) throws SQLException {
        return userRepo.Check(email);
    }
    public void resetPass(String email, String password) throws SQLException {
        userRepo.resetPass(email, password);
    }
    public void changePass(String aid, String password) throws SQLException {
         userRepo.changePass(aid, password);
    }
//    public void createAccount(String phone, String email, String password, String name, int blockId, int roleId) throws SQLException{
//        userRepo.createAccount(phone, email, password, name, blockId, roleId);
//    }
    public ArrayList<UserEntity> getAllUser() throws Exception{
        return userRepo.getAllUser();
    }
    
    public ArrayList<UserEntity> getAllUserByName(String name) throws Exception{
        return userRepo.getAllUserByName(name);
    }
    public String getUserForChart() throws SQLException {
        return userRepo.getTopUserJsArray();
    }
    public String getUserMoneyForChart() throws SQLException {
        return userRepo.getTopUserTotalMoneyJsArray();
    }
    
    public int getCountResident() throws Exception {
        return userRepo.getCountResident();
    }
    
    public boolean addNewResident(UserEntity entity) throws Exception {
        if (userRepo.Check(entity.getEmail()) != null) {
            return false;
        }
        userRepo.addNewResident(entity);
        return true;
    }
    
    public UserEntity getUser(String aid) throws SQLException {
        return userRepo.getUser(aid);
    }
    
    public UserEntity getManagerOfBlock(int blockId) throws SQLException {
        return userRepo.getManagerOfBlock(blockId);
    }
    
    public void updateInfo(String username, String gender, int bid, String phone, int aid) throws SQLException{
        userRepo.updateProfile(username, gender, bid, phone, aid);
    }
    
    public static void main(String[] args) throws Exception {
        UserService service = new UserService();
        System.out.println(service.getCountResident());
    }
}

