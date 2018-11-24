package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {
ProfilePicture  saveOrUpdateProfilePic(ProfilePicture profilePicture);
ProfilePicture  getImage(String email);
}