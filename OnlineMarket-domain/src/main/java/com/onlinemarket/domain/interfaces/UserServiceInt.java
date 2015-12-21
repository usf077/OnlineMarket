/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlinemarket.domain.interfaces;

import com.onlinemarket.domain.dtos.userDTO;
import javax.ejb.Remote;

/**
 *
 * @author ADMINIBM
 */
@Remote
public interface UserServiceInt {
    public userDTO getUserByUserName(String email);
    public userDTO createUser(userDTO user);
}
