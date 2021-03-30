package com.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jdbc.JdbcUtils;

public class ClientDao implements ClientService {

	private JdbcUtils jdbcUtils;

	public ClientDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	@Override
	public boolean addClient(List<Object> params) {
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "insert into client(clientid,name,gender,DOB,Phone,Job,Other) values(?,?,?,?,?,?,?)";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			// 关闭数据库连接
			jdbcUtils.releaseConn();

		}

		return flag;
	}

	@Override
	public boolean updateClient(List<Object> params) {

		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String sql = "";
			sql = "update client set name= ?,gender= ?,DOB= ?,Phone= ?,Job= ?,Other = ?  where clientid = ?";
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			// 关闭数据库连接
			jdbcUtils.releaseConn();

		}

		return flag;
	}

	@Override
	public List<Map<String, Object>> listClient(String clientName,String clientSex,String clientDOB, int start, int end) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Object> params = new ArrayList<Object>();
		try {
			jdbcUtils.getConnection();
			String sql = "";
			if (clientName.equals("")&& clientSex.equals("") && clientDOB.equals("")) {
				sql = "select * from client limit ? ,?";
				params.add(start);
				params.add(end);
			} else if (!clientName.equals("") && clientSex.equals("") && clientDOB.equals("")) {
				sql = "select * from client where 1=1 and name like ? limit ? ,?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
				params.add(start);
				params.add(end);
			} else if (!clientName.equals("") && !clientSex.equals("") && clientDOB.equals("")) {
				sql = "select * from client where 1=1 and name like ? and gender like ? limit ? ,?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
				params.add("%"+clientSex+"%");
				params.add(start);
				params.add(end);
			} else if (!clientName.equals("") && !clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select * from client where 1=1 and name like ? and gender like ? and DOB like ? limit ? ,?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
				params.add("%"+clientSex+"%");
				params.add("%"+clientDOB+"%");
				params.add(start);
				params.add(end);
			} else if (clientName.equals("") && !clientSex.equals("") && clientDOB.equals("")) {
				sql = "select * from client where 1=1 and gender like ? limit ? ,?";
				params = new ArrayList<Object>();
				params.add("%"+clientSex+"%");
				params.add(start);
				params.add(end);
			} else if (clientName.equals("") && !clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select * from client where 1=1 and gender like ? and DOB like ? limit ? ,?";
				params = new ArrayList<Object>();
				params.add("%" + clientSex + "%");
				params.add("%"+clientDOB+"%");
				params.add(start);
				params.add(end);
			} else if (clientName.equals("") && clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select * from client where 1=1 and DOB like ? limit ? ,?";
				params = new ArrayList<Object>();
				params.add("%" + clientDOB + "%");
				params.add(start);
				params.add(end);
			} else if (!clientName.equals("") && clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select * from client where 1=1 and name like ? and DOB like ? limit ? ,?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
				params.add("%" + clientDOB + "%");
				params.add(start);
				params.add(end);
			}
			
//			String sql = "select * from client where 1=1 and name like ? limit ? ,?";
//			if (clientName.equals("")) {
//				sql = "select * from client limit ? ,?";
//				params.add(start);
//				params.add(end);
//
//			} else {
//				params.add("%" + clientName + "%");
//				params.add(start);
//				params.add(end);
//			}

			list = jdbcUtils.findMoreResult(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			jdbcUtils.releaseConn();

		}

		return list;
	}

	// 查询总记录
	@Override
	public int getItemCount(String clientName, String clientSex, String clientDOB) {
		// TODO Auto-generated method stub
		int count = 0;
		Map<String, Object> map = null;
		List<Object> params = null;
		try {
			jdbcUtils.getConnection();
			String sql = "";
			if (clientName.equals("")&& clientSex.equals("") && clientDOB.equals("")) {
				sql = "select count(*) totalCount from client";
			} else if (!clientName.equals("") && clientSex.equals("") && clientDOB.equals("")) {
				sql = "select count(*) totalCount from client where 1=1 and name like ?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
			} else if (!clientName.equals("") && !clientSex.equals("") && clientDOB.equals("")) {
				sql = "select count(*) totalCount from client where 1=1 and name like ? and gender like ?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
				params.add("%"+clientSex+"%");
			} else if (!clientName.equals("") && !clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select count(*) totalCount from client where 1=1 and name like ? and gender like ? and DOB like ?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
				params.add("%"+clientSex+"%");
				params.add("%"+clientDOB+"%");
			} else if (clientName.equals("") && !clientSex.equals("") && clientDOB.equals("")) {
				sql = "select count(*) totalCount from client where 1=1 and gender like ?";
				params = new ArrayList<Object>();
				params.add("%"+clientSex+"%");
			} else if (clientName.equals("") && !clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select count(*) totalCount from client where 1=1 and gender like ? and DOB like ?";
				params = new ArrayList<Object>();
				params.add("%" + clientSex + "%");
				params.add("%"+clientDOB+"%");
			} else if (clientName.equals("") && clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select count(*) totalCount from client where 1=1 and DOB like ?";
				params = new ArrayList<Object>();
				params.add("%" + clientDOB + "%");
			} else if (!clientName.equals("") && clientSex.equals("") && !clientDOB.equals("")) {
				sql = "select count(*) totalCount from client where 1=1 and name like ? and DOB like ?";
				params = new ArrayList<Object>();
				params.add("%" + clientName + "%");
				params.add("%" + clientDOB + "%");
			}
			map = jdbcUtils.findSimpleResult(sql, params);
			count = Integer.parseInt(map.get("totalCount").toString());
			System.out.print(count);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}

		return count;
	}

	@Override
	public boolean delClient(String[] ids) {
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			if (ids != null) {
				String[] sql = new String[ids.length];
				for (int i = 0; i < ids.length; i++) {
					sql[i] = "delete from client where clientid = '" + ids[i] + "'";
					System.out.println(sql[i]);
				}
				flag = jdbcUtils.deleteByBatch(sql);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}

		return flag;
	}

	@Override
	public Map<String, Object> viewClient(String clientid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
		try {
			jdbcUtils.getConnection();
			List<Object> params = new ArrayList<Object>();
			params.add(clientid);
			String sql = "select * from client where clientid = ?";
			map = jdbcUtils.findSimpleResult(sql, params);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			jdbcUtils.releaseConn();
		}

		return map;
	}

}
