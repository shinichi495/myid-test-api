package com.unifiedpost.myid.userservice.db.mapper;

import com.unifiedpost.myid.userservice.db.Converter;
import com.unifiedpost.myid.userservice.db.UserModel;
import com.unifiedpost.myid.userservice.entities.User;

public class UserMapper {

  public static User toEntity(UserModel model) {
    if (model == null) {
      return null;
    }
    User user = new User();
    Converter.convert(model, user);
    return user;
  }

  public static UserModel toModel(User entity) {
    if (entity == null) {
      return null;
    }
    UserModel model = new UserModel();
    Converter.convert(entity, model);
    return model;
  }
}
