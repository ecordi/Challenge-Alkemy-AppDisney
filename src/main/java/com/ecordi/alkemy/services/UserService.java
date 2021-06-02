package com.ecordi.alkemy.services;

import com.ecordi.alkemy.entities.User;
import com.ecordi.alkemy.entities.DTO.ChangePasswordForm;
import com.ecordi.alkemy.others.exceptions.UsernameOrIdNotFound;

public interface UserService {

	public Iterable<User> getAllUsers();
	public User createUser(User user) throws Exception;
	public User getUserById(Long id) throws UsernameOrIdNotFound;
    public User updateUser(User user) throws Exception;        
    public void deleteUser(Long id) throws UsernameOrIdNotFound;        
    public User changePassword(ChangePasswordForm form) throws Exception;


}
