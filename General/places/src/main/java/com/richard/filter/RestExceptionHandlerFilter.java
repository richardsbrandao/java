package com.richard.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.richard.errors.InternalServerException;
import com.richard.errors.RestHttpException;

public class RestExceptionHandlerFilter implements Filter {

	private static final Log LOG = LogFactory.getLog( RestExceptionHandlerFilter.class );

	private String acceptHeader;
	
    public RestExceptionHandlerFilter() { }

	public void destroy() { }

	public void init(FilterConfig fConfig) throws ServletException { }
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		acceptHeader = getHeader(request);
		response.setContentType( acceptHeader );
		
		try {
			chain.doFilter(request, response);
		} catch( RestHttpException e ) {
			handleRestException(e, request, response);
			LOG.error("Filtro de erros capturou excecao.", e);
		} catch (Exception e) {
			RestHttpException cause = e.getCause() instanceof RestHttpException ? (RestHttpException) e.getCause() : new InternalServerException() ;
			handleRestException(cause, request, response);
			LOG.error("Filtro de erros capturou uma excecao inexperada.", e);
		}
	}

	private String getHeader(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader("Accept");
		return header != null ? header : "application/json";
	}

	private void handleRestException(RestHttpException e, ServletRequest request, ServletResponse response) throws IOException {
		String[] accepts = acceptHeader.split(",");
		String message = e.to(accepts);
		
		((HttpServletResponse) response).setStatus( e.getStatus() );
		
		PrintWriter writer = response.getWriter();
		writer.write( message );
	}
	
}
