package com.sliit.ds.FastFoodOnlineIT16032798.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class Session {

    @Id
    private long uid;
    private long authKeyOfUid;

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
}
