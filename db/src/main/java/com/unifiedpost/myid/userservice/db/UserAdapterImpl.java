package com.unifiedpost.myid.userservice.db;

import com.unifiedpost.myid.userservice.db.mapper.UserMapper;
import com.unifiedpost.myid.userservice.entities.User;
import com.unifiedpost.myid.userservice.usecases.adapter.UserAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import java.util.Optional;
@Slf4j
public class UserAdapterImpl implements UserAdapter {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User save(User user) {
    if (user == null) {
      log.info("user null");
      return null;
    }
    UserModel model = UserMapper.toModel(user);
    log.info("Namph usermodel testing with dossier" + model.getDossierId());
    model = userRepository.save(model);
    return UserMapper.toEntity(model);
  }

  @Override
  public User findByEmail(String email) {
    if (!StringUtils.hasText(email)) {
      return null;
    }
    Optional<UserModel> userOptional = userRepository.findByEmail(email);
    return userOptional.isPresent() ? UserMapper.toEntity(userOptional.get()) : null;
  }

}
