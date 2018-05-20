package com.sliit.ds.FastFoodOnlineIT16032798.service;

import com.sliit.ds.FastFoodOnlineIT16032798.model.Session;

public interface SessionService {

    Session getSessionOf(long uid);     // returns both authkey and the uid as an Object.

    long getUidOfSession(long authKey); // returns the uid of the user to whom the auth key belongs.

    boolean authenticate(long autheKey);// simply checks if the auth key exists in the db :-)

    void saveSession(Session session);

    void invokeSession(Session session);

    void invokeSessionOfUser(long uid); // delete the session entry that belongs to the uid.

    void invokeSessionOfKey(long authKey);// delete the session entry that contains the given auth key.
}
