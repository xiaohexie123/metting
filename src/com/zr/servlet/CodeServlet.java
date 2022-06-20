package com.zr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;
import com.zr.util.ResultUtil;

@WebServlet("/code")
public class CodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		String oldCode = (String)session.getAttribute("code");
		ResultUtil result = new ResultUtil();
		//返回JSON字符串给前端
		if(code.equals(oldCode)) {
			result.setSuccess(true);
			result.setMsg("验证码正确");
		}else {
			result.setSuccess(false);
			result.setMsg("验证码错误");
		}
		PrintWriter out = resp.getWriter();
		out.write(JSON.toJSONString(result));
		out.flush();
		out.close();
	}
	
	

}
