package com.yongle.goku.system.filter;

import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    private PrintWriter printWriter;
    private ServletOutputStream out;

    public BodyReaderHttpServletResponseWrapper(HttpServletResponse httpServletResponse) throws UnsupportedEncodingException {
        super(httpServletResponse);
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
        printWriter = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (printWriter != null) {
            printWriter.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    public String getContent() throws IOException {
        String contentType = getResponse().getContentType();
        if (contentType != null && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            byte[] b = getResponseData();
            if (b != null) {
                String content = new String(b, Charset.forName("UTF-8"));
                getResponse().reset();
                getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
                getResponse().getWriter().print(content);
                return content;
            }
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

        @Override
        public void write(byte[] b) throws IOException {
            bos.write(b, 0, b.length);
        }
    }
}