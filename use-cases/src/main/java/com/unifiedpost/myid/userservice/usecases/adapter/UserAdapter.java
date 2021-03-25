package com.unifiedpost.myid.userservice.usecases.adapter;

import com.unifiedpost.myid.userservice.entities.User;

public interface UserAdapter {

  User save(User user);

  User findByEmail(String email);


}
