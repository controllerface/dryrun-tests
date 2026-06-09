package org.test.fileutils;

import org.test.VulnerableServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class FileUtils_FileGet_wDir extends VulnerableServlet
{
    private static final FileUtils_FileGet_wDir servlet = new FileUtils_FileGet_wDir();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        var bytes = req.getInputStream().readAllBytes();
        var filename = new String(bytes, Charset.defaultCharset());
        var parent = new File("parent_dir");
        var file = FileUtils.getFile(parent, filename);
        resp.setStatus(200);
        resp.getOutputStream().println(Files.readString(file.toPath()));
    }

    public void run() throws IOException
    {
        servlet.service(new VulnerableRequest(), new VulnerableResponse());
    }
}
