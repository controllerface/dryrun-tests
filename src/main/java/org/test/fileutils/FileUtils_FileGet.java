package org.test.fileutils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.test.VulnerableServlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class FileUtils_FileGet extends VulnerableServlet
{
    private static final FileUtils_FileGet servlet = new FileUtils_FileGet();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        var bytes = req.getInputStream().readAllBytes();
        var filename = new String(bytes, Charset.defaultCharset());
        var file = FileUtils.getFile(filename);
        resp.setStatus(200);
        resp.getOutputStream().println(Files.readString(file.toPath()));
    }

    public void run() throws IOException
    {
        servlet.service(new VulnerableServlet.VulnerableRequest(), new VulnerableServlet.VulnerableResponse());
    }
}
