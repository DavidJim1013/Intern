package com.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.util.DividePage;
import com.util.UUIDTools;

@SuppressWarnings("serial")
public class ClientAction extends HttpServlet {

	private ClientService service;

	/**
	 * Constructor of the object.
	 */
	public ClientAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addClient(request, response);
		} else if (action_flag.equals("search")) {
			listClient(request, response);
		} else if (action_flag.equals("del")) {
			delClient(request, response);
		} else if (action_flag.equals("viewUpdate")) {
			viewUpdateClient(request, response);
		} else if (action_flag.equals("update")) {
			updateClient(request, response);
		}

		out.flush();
		out.close();
	}

	private void viewUpdateClient(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String clientid = request.getParameter("clientid");
		Map<String, Object> map = service.viewClient(clientid);
		request.setAttribute("clientMap", map);
		try {
			request.getRequestDispatcher("/updateInf.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 批量删除客户
	 * 
	 * @param request
	 * @param response
	 */
	private void delClient(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// 获取选中的值
		String[] ids = request.getParameterValues("ids");
		for (int i = 0; i < ids.length; i++) {
			System.out.println("ids[" + i + "]=" + ids[i]);
		}
		boolean flag = service.delClient(ids);
		System.out.println("删除flag:" + flag);
		if (flag) {
			try {
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void listClient(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String clientName = request.getParameter("clientName");
		String clientSex = request.getParameter("Searchsex");
		String clientDOB = request.getParameter("SearchDOB");
		System.out.print("clientName:"+clientName+" clientSex:"+clientSex+" clientDOB:"+clientDOB);
		String pageNum = request.getParameter("pageNum");
		System.out.println("参数 pageNum :" + pageNum);
		if (clientName == null) {
			clientName = "";
		}
		if (clientSex == null) {
			clientSex = "";
		}
		if (clientDOB == null) {
			clientDOB = "";
		}
		
		
		int totalRecord = service.getItemCount(clientName,clientSex,clientDOB); // 获取总个数
		int currentPage = 1;
		
		DividePage dividePage = new DividePage(5, totalRecord);// 默认第一页开始
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
			dividePage.setCurrentPage(currentPage);
		}

		// 第几行开始
		int start = dividePage.fromIndex();
		// 显示几条
		int end = dividePage.toIndex();

		System.out.println("currentPageNum :" + dividePage.getCurrentPage() + ", start = " + start + ", end = " + end);

		List<Map<String, Object>> list = null;
		try {
			list = service.listClient(clientName,clientSex,clientDOB, start, end);
			request.setAttribute("listClient", list);
			request.setAttribute("dividePage", dividePage);
			request.setAttribute("clientName", clientName);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 新增客户
	private void addClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 除了基本信息，还可以实现上传客户照片
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3 * 1024 * 1024);// 最大单个文件3MB
		servletFileUpload.setSizeMax(6 * 1024 * 1024);// 总文件最大6MB
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		String DOB = "";
		params.add(UUIDTools.getUUID()); // 随机生成id
		try {
			// 解析request请求
			list = servletFileUpload.parseRequest(request);
			// 取出表单的值
			for (FileItem fileItem : list) {
				String fileItemName = fileItem.getFieldName();// 获取<input>控件的名字
				String fileItemValue = fileItem.getString("utf-8");// 获取<input>控件的值
				if (fileItemName.equals("name")) {
					params.add(fileItemValue); //姓名
				} else if (fileItemName.equals("sex")) {
					params.add(fileItemValue);// 性别
				} else if (fileItemName.equals("year")) {
					if (!fileItemValue.isEmpty()) { // 判断是否为空
						DOB += fileItemValue + "/"; // 不为空增加间隔符
					}
				} else if (fileItemName.equals("month")) {
					if (!fileItemValue.isEmpty()) { // 判断是否为空
						DOB += fileItemValue + "/"; // 不为空增加间隔符
					}
				} else if (fileItemName.equals("day")) {
					if (fileItemValue.length() == 1) {
						fileItemValue = '0' + fileItemValue; //自动补齐
					} 
					DOB += fileItemValue;
					params.add(DOB); // 生日
				} else if (fileItemName.equals("Phone")) {
					params.add(fileItemValue);// 手机
				} else if (fileItemName.equals("Job")) {
					params.add(fileItemValue);// 职业
				} else if (fileItemName.equals("remark")) {
					params.add(fileItemValue);// 备注
				} 
			}

			// 添加客户到数据库
			boolean flag = service.addClient(params);
			if (flag) {
				response.sendRedirect(path + "/main.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 修改客户信息
	private void updateClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(6 * 1024 * 1024);
		String DOB = "";
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		try {
			// 解析request请求
			list = servletFileUpload.parseRequest(request);
			// 取出表单的值
			for (FileItem fileItem : list) {
				String fileItemName = fileItem.getFieldName();// 获取<input>控件的名字
				String fileItemValue = fileItem.getString("utf-8");// 获取<input>控件的值
				if (fileItemName.equals("name")) {
					params.add(fileItemValue); //姓名
				} else if (fileItemName.equals("sex")) {
					params.add(fileItemValue);// 性别
				} else if (fileItemName.equals("year")) {
					if (!fileItemValue.isEmpty()) { // 判断是否为空
						DOB += fileItemValue + "/"; // 不为空增加间隔符
					}
				} else if (fileItemName.equals("month")) {
					if (!fileItemValue.isEmpty()) { // 判断是否为空
						DOB += fileItemValue + "/"; // 不为空增加间隔符
					}
				} else if (fileItemName.equals("day")) {
					if (fileItemValue.length() == 1) {
						fileItemValue = '0' + fileItemValue; //自动补齐
					} 
					DOB += fileItemValue;
					params.add(DOB); // 生日
				} else if (fileItemName.equals("Phone")) {
					params.add(fileItemValue);// 手机
				} else if (fileItemName.equals("Job")) {
					params.add(fileItemValue);// 职业
				} else if (fileItemName.equals("remark")) {
					params.add(fileItemValue);// 备注
				} else if (fileItemName.equals("clientid")) {
					params.add(fileItemValue);
				}
			}			

			// 更新客户信息到数据库
			boolean flag = service.updateClient(params);
			if (flag) {
				response.sendRedirect(path + "/main.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new ClientDao();
	}

}
