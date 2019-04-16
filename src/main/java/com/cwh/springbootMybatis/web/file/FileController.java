package com.cwh.springbootMybatis.web.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cwh.springbootMybatis.util.FileUtils;
import com.cwh.springbootMybatis.util.GeneralReturn;

/**
 * @since file upload class
 * @author HuWang
 * 
 */
@RestController
@RequestMapping("/web/")
public class FileController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * @Autowired private UserInfo user;
	 */
	/**
	 * @since file upload method
	 * @param request
	 * @param file
	 * @param session
	 * @return GeneralReturn
	 */
	@RequestMapping("/FileUpload")
	public GeneralReturn upload_File(HttpServletRequest request,
			MultipartFile file, HttpSession session) {
		JSONObject resJson = new JSONObject();
		String path = request.getServletContext().getRealPath("/");
		System.out.println("path==" + path);
		String path2 = FileUtils.uploads(file, path);
		return GeneralReturn.build(1, "success", path2);
	}

}
