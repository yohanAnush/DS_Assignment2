package com.sliit.ds.FastFoodOnlineIT16032798.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class Session {

    @Id
    private long uid;
    private long authKeyOfUid;
    private String role;

    public Session() { }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getAuthKeyOfUid() {
        return authKeyOfUid;
    }

    public void setAuthKeyOfUid(long authKeyOfUid) {
        this.authKeyOfUid = authKeyOfUid;
    }

    public String getRole() {
        return role;
    }

    // we don't create any role beside user since admin roles are already created and won't invole
    // registering.
    public void setRole(String role) {
        this.role = role;
    }
}
