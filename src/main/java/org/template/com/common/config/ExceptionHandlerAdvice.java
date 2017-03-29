package org.template.com.common.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

	private Boolean isJson(WebRequest request) {
		String header = request.getHeader("content-type");

		if (!StringUtils.isEmpty(header)) {
			return header.contains("json");
		}

		return true;

	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public void handleBaseException(HttpServletRequest request, HttpServletResponse response, Exception e) {

		
		log.error("全局异常处理错误", e);
		try {
			outputMessage(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}

	private void outputMessage(HttpServletResponse response, int errCode, String errMsg) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", errCode);
		map.put("msg", errMsg);
		ObjectMapper mapper = new ObjectMapper(); 
		String json = mapper.writeValueAsString(map);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.setStatus(errCode);
		ServletOutputStream os = response.getOutputStream();
		os.write(json.getBytes("utf-8"));
	}

	// 用来设置WebDataBinder， WebDataBinder用来自动绑定前台请求参数到后台Model中
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {

	}

	@ModelAttribute
	public void addAttribute(Model model) {
		model.addAttribute("msg", "额外信息");
	}
}
