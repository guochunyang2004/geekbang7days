package com.gcy.demo.ioc.overview.dependency.injection;

import com.gcy.demo.ioc.overview.domain.User;

public class UserHolder {

    public UserHolder(){

    }
    public UserHolder(User user){
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }

    private User user;
}
