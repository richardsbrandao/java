package com.richard.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.richard.errors.BadRequestException;

public class AbstractController {

	private static Log LOG = LogFactory.getLog(AbstractController.class);
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public @ResponseBody Serializable handleBadRequest(HttpMessageNotReadableException e, HttpServletResponse response) throws BadRequestException {
		LOG.error("Erro ao fazer o parse na Request ", e);
		return new BadRequestException(e.getMessage()).toString();
	}
	
}
