/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.ResidentEntity;
import entity.UserEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import repository.ResidentRepository;

public class ResidentService {
    
    ResidentRepository residentRepo = new ResidentRepository();
    
    public ResidentEntity read(String aid) throws SQLException {
        return residentRepo.read(aid);
    }
    
    public ArrayList<UserEntity> getAllResidentByName(String residentName) throws Exception{
        return residentRepo.getAllResidentByName(residentName);
    }
    
    public UserEntity getOne(int AID) throws SQLException{
        return residentRepo.getOne(AID);
    }
    
    public void updateRoom(String room, int AID) throws SQLException{
        residentRepo.updateRoom(room, AID);
    }
    
    public void updateResident(String room, int BID, int status, int AID) throws SQLException{
        residentRepo.updateResident(room, BID, status, AID);
    }
    
    
}
