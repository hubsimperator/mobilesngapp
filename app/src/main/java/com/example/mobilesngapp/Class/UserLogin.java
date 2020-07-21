package com.example.mobilesngapp.Class;

public class UserLogin {

    public String PassHash;
    public String ResourceId;
    public String TridentUserId;
    public String StrukturaId;
    public String UserId;
    public Integer Status;

    public UserLogin(String passHash, String resourceId, String tridentUserId, String strukturaId, String userId, String status) {
        PassHash = passHash;
        ResourceId = resourceId;
        TridentUserId = tridentUserId;
        StrukturaId = strukturaId;
        UserId = userId;
        Status = Integer.parseInt(status) ;
    }

    public String getPassHash() {
        return PassHash;
    }

    public void setPassHash(String passHash) {
        PassHash = passHash;
    }

    public String getResourceId() {
        return ResourceId;
    }

    public void setResourceId(String resourceId) {
        ResourceId = resourceId;
    }

    public String getTridentUserId() {
        return TridentUserId;
    }

    public void setTridentUserId(String tridentUserId) {
        TridentUserId = tridentUserId;
    }

    public String getStrukturaId() {
        return StrukturaId;
    }

    public void setStrukturaId(String strukturaId) {
        StrukturaId = strukturaId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}

