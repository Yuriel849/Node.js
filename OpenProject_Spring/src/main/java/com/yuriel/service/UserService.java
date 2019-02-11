package com.yuriel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yuriel.domain.UserListVO;
import com.yuriel.domain.UserVO;

public interface UserService {
	public int createUser(UserVO vo, HttpServletRequest request) throws Exception;
		
	public UserVO getUser(String email) throws Exception;
	
	public UserListVO getUserList(int count, int page) throws Exception;
	
	public List<UserVO> getAllUsersList() throws Exception;
	
	public void deleteUser(String ID) throws Exception;
}