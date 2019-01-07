package com.ale.threadlocal;

import com.ale.entity.User;

public class UserThreadLocal{

    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static User getUser() {
        return USER_THREAD_LOCAL.get();
    }

    public static void setUser(User user){
        USER_THREAD_LOCAL.set(user);
    }

    public static String getUserId(){
        try {
            return USER_THREAD_LOCAL.get().getUserId();
        } catch (Exception e) {
            return null;
        }

    }


}
