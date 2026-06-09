package org.test.ioutils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.test.VulnerableServlet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class IOUtils_InputBuffer extends VulnerableServlet
{
    private static final IOUtils_InputBuffer servlet = new IOUtils_InputBuffer();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        var buffer = IOUtils.buffer(req.getInputStream());
        var bytes = buffer.readAllBytes();
        var filename = new String(bytes, Charset.defaultCharset());
        var file = new File(filename);
        resp.setStatus(200);
        resp.getOutputStream().println(Files.readString(file.toPath()));
    }

    public void run() throws IOException
    {
        servlet.service(new VulnerableRequest(), new VulnerableResponse());
    }
}
