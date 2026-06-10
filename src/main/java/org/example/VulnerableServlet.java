package org.example;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ReadListener;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConnection;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public abstract class VulnerableServlet extends HttpServlet
{
    public static class DummyResponseStream extends ServletOutputStream
    {
        @Override
        public boolean isReady()
        {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener)
        {

        }

        @Override
        public void write(int b)
        {

        }
    }

    public static class VulnerableRequest implements HttpServletRequest
    {
        String tainted_data = "";

        public void set_tainted_data(String tainted_data)
        {
            this.tainted_data = tainted_data;
        }

        @Override
        public String getAuthType()
        {
            return null;
        }

        @Override
        public Cookie[] getCookies()
        {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String s)
        {
            return 0;
        }

        @Override
        public String getHeader(String s)
        {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String s)
        {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames()
        {
            return null;
        }

        @Override
        public int getIntHeader(String s)
        {
            return 0;
        }

        @Override
        public String getMethod()
        {
            return null;
        }

        @Override
        public String getPathInfo()
        {
            return null;
        }

        @Override
        public String getPathTranslated()
        {
            return null;
        }

        @Override
        public String getContextPath()
        {
            return null;
        }

        @Override
        public String getQueryString()
        {
            return null;
        }

        @Override
        public String getRemoteUser()
        {
            return null;
        }

        @Override
        public boolean isUserInRole(String s)
        {
            return false;
        }

        @Override
        public Principal getUserPrincipal()
        {
            return null;
        }

        @Override
        public String getRequestedSessionId()
        {
            return null;
        }

        @Override
        public String getRequestURI()
        {
            return null;
        }

        @Override
        public StringBuffer getRequestURL()
        {
            return null;
        }

        @Override
        public String getServletPath()
        {
            return null;
        }

        @Override
        public HttpSession getSession(boolean b)
        {
            return null;
        }

        @Override
        public HttpSession getSession()
        {
            return null;
        }

        @Override
        public String changeSessionId()
        {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid()
        {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie()
        {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL()
        {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse httpServletResponse)
        {
            return false;
        }

        @Override
        public void login(String s, String s1)
        {

        }

        @Override
        public void logout()
        {

        }

        @Override
        public Collection<Part> getParts()
        {
            return null;
        }

        @Override
        public Part getPart(String s)
        {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass)
        {
            return null;
        }

        @Override
        public Object getAttribute(String s)
        {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames()
        {
            return null;
        }

        @Override
        public String getCharacterEncoding()
        {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s)
        {

        }

        @Override
        public int getContentLength()
        {
            return 0;
        }

        @Override
        public long getContentLengthLong()
        {
            return 0;
        }

        @Override
        public String getContentType()
        {
            return null;
        }

        @Override
        public ServletInputStream getInputStream()
        {
            var input = new ByteArrayInputStream(tainted_data.getBytes(Charset.defaultCharset()));
            return new ServletInputStream()
            {
                @Override
                public boolean isFinished()
                {
                    return false;
                }

                @Override
                public boolean isReady()
                {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener)
                {

                }

                @Override
                public int read()
                {
                    return input.read();
                }
            };
        }

        @Override
        public String getParameter(String s)
        {
            return null;
        }

        @Override
        public Enumeration<String> getParameterNames()
        {
            return null;
        }

        @Override
        public String[] getParameterValues(String s)
        {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap()
        {
            return null;
        }

        @Override
        public String getProtocol()
        {
            return null;
        }

        @Override
        public String getScheme()
        {
            return null;
        }

        @Override
        public String getServerName()
        {
            return null;
        }

        @Override
        public int getServerPort()
        {
            return 0;
        }

        @Override
        public BufferedReader getReader()
        {
            return null;
        }

        @Override
        public String getRemoteAddr()
        {
            return null;
        }

        @Override
        public String getRemoteHost()
        {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o)
        {

        }

        @Override
        public void removeAttribute(String s)
        {

        }

        @Override
        public Locale getLocale()
        {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales()
        {
            return null;
        }

        @Override
        public boolean isSecure()
        {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String s)
        {
            return null;
        }

        @Override
        public int getRemotePort()
        {
            return 0;
        }

        @Override
        public String getLocalName()
        {
            return null;
        }

        @Override
        public String getLocalAddr()
        {
            return null;
        }

        @Override
        public int getLocalPort()
        {
            return 0;
        }

        @Override
        public ServletContext getServletContext()
        {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException
        {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException
        {
            return null;
        }

        @Override
        public boolean isAsyncStarted()
        {
            return false;
        }

        @Override
        public boolean isAsyncSupported()
        {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext()
        {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType()
        {
            return null;
        }

        @Override
        public String getRequestId()
        {
            return null;
        }

        @Override
        public String getProtocolRequestId()
        {
            return null;
        }

        @Override
        public ServletConnection getServletConnection()
        {
            return null;
        }
    }

    public static class VulnerableResponse implements HttpServletResponse
    {
        @Override
        public void addCookie(Cookie cookie)
        {

        }

        @Override
        public boolean containsHeader(String s)
        {
            return false;
        }

        @Override
        public String encodeURL(String s)
        {
            return null;
        }

        @Override
        public String encodeRedirectURL(String s)
        {
            return null;
        }

        @Override
        public void sendError(int i, String s)
        {

        }

        @Override
        public void sendError(int i)
        {

        }

        @Override
        public void sendRedirect(String s)
        {

        }

        @Override
        public void setDateHeader(String s, long l)
        {

        }

        @Override
        public void addDateHeader(String s, long l)
        {

        }

        @Override
        public void setHeader(String s, String s1)
        {

        }

        @Override
        public void addHeader(String s, String s1)
        {

        }

        @Override
        public void setIntHeader(String s, int i)
        {

        }

        @Override
        public void addIntHeader(String s, int i)
        {

        }

        @Override
        public void setStatus(int i)
        {

        }

        @Override
        public int getStatus()
        {
            return 0;
        }

        @Override
        public String getHeader(String s)
        {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String s)
        {
            return null;
        }

        @Override
        public Collection<String> getHeaderNames()
        {
            return null;
        }

        @Override
        public String getCharacterEncoding()
        {
            return null;
        }

        @Override
        public String getContentType()
        {
            return null;
        }

        @Override
        public ServletOutputStream getOutputStream()
        {
            return new DummyResponseStream();
        }

        @Override
        public PrintWriter getWriter()
        {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s)
        {

        }

        @Override
        public void setContentLength(int i)
        {

        }

        @Override
        public void setContentLengthLong(long l)
        {

        }

        @Override
        public void setContentType(String s)
        {

        }

        @Override
        public void setBufferSize(int i)
        {

        }

        @Override
        public int getBufferSize()
        {
            return 0;
        }

        @Override
        public void flushBuffer()
        {

        }

        @Override
        public void resetBuffer()
        {

        }

        @Override
        public boolean isCommitted()
        {
            return false;
        }

        @Override
        public void reset()
        {

        }

        @Override
        public void setLocale(Locale locale)
        {

        }

        @Override
        public Locale getLocale()
        {
            return null;
        }
    }
}
