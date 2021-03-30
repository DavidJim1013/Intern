package com.client;

import java.util.List;
import java.util.Map;

public interface ClientService {
	public boolean addClient(List<Object> params);

	public boolean updateClient(List<Object> params);

	//列出客户 start,end
	public List<Map<String, Object>> listClient(String clientname, String clientSex, String clientDOB, int start, int end);

	// 获取总客户数
	public int getItemCount(String clientName, String clientSex, String clientDOB);

	// 批量删除客户
	public boolean delClient(String[] ids);

	//
	public Map<String, Object> viewClient(String clientid);
}
