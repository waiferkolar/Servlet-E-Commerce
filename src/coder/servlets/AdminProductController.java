package coder.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

@WebServlet("/AdminProductController")
//@MultipartConfig
public class AdminProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public AdminProductController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initContext = new InitialContext();
			Context env = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String param = request.getParameter("action");
		String filePath = "C:\\waifer\\Softwares\\J2EE\\J2EE-Wrokspace\\E-Commerce\\WebContent\\assets\\uploads";
		if (param.contentEquals("newproduct")) {
			String cat_id = request.getParameter("cat_id");
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String description = request.getParameter("description");

			pw.write("Cat Id : " + cat_id + "\nName : " + name + "\nPrice : " + price + "\nDescription " + description
					+ "\n");

			if (ServletFileUpload.isMultipartContent(request)) {
				try {

					FileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					List<FileItem> multiparts = upload.parseRequest(new ServletRequestContext(request));

					pw.write("Multipart Count " + multiparts);

					for (FileItem item : multiparts) {
						if (!item.isFormField()) {
							String name1 = new File(item.getName()).getName();
							item.write(new File(filePath + File.separator + name1));
						}
					}

				} catch (FileUploadException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			pw.write("No Data");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
