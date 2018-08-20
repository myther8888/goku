package com.yongle.goku.system.filter;

import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * <p>类 名 称：BodyReaderHttpServletResponseWrapper.java
 * <p>功能说明：
 * <p>开发时间：2018年08月20日
 *
 * @author ：weinh
 */
public class BodyReaderHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream buffer;

    private ServletOutputStream out;

    public BodyReaderHttpServletResponseWrapper(HttpServletResponse httpServletResponse) {
        super(httpServletResponse);
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
    }

    public String getContent() throws IOException {
        flushBuffer();
        String contentType = getResponse().getContentType();
        if (contentType != null && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            byte[] b = buffer.toByteArray();
            String content = new String(b, Charset.forName("UTF-8"));
            getResponse().reset();
            getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
            getResponse().getWriter().print(content);
            return content;
        }
        return null;
    }

    class WrapperOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos;

        public WrapperOutputStream(ByteArrayOutputStream bos) {
            this.bos = bos;
        }

        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }
    }
}