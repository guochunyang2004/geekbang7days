package com.gcy.demo.ioc.overview.domain;

import com.gcy.demo.ioc.overview.annotation.Super;

/**
 * 超级用户
 */
@Super
public class SuperUser extends User {
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private  String address;

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
