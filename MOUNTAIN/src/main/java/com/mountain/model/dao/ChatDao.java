package com.mountain.model.dao;

import java.util.List;

import com.mountain.model.dto.Chat;

public interface ChatDao {
	public List<Chat> selectAll();
	public List<Chat> selectChat();

	
	
}
