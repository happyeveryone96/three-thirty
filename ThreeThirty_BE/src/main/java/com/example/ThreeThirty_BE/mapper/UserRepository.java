package com.example.ThreeThirty_BE.mapper;

import com.example.ThreeThirty_BE.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

  void saveUser(User user_id);

  User findByEmail(@Param("user_email") String user_email);

  User findById(Long user_id);

  void updateUser(@Param("user_id") Long user_id, @Param("nick_name") String nick_name);

  void updateUserPassword(@Param("user_id") Long user_id, @Param("pw") String pw);

  void addUserInfo(Long user_id, String phone_number, String image_url, String nick_name);

  boolean findByPhoneNum(String phone_number);

  boolean findByNickName(String nick_name);
}
