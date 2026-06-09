package org.test.fileutils;

import org.test.VulnerableServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class FileUtils_UrlToFile extends VulnerableServlet
{
    private static final FileUtils_UrlToFile servlet = new FileUtils_UrlToFile();

    private static URL filename_as_url(String filename)
    {
        try
        {
            return URI.create(filename).toURL();
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        var bytes = req.getInputStream().readAllBytes();
        var filename = new String(bytes, Charset.defaultCharset());
        var fileurl = filename_as_url(filename);
        var file = FileUtils.toFile(fileurl);
        resp.setStatus(200);
        resp.getOutputStream().println(Files.readString(file.toPath()));
    }

    public void run() throws IOException
    {
        servlet.service(new VulnerableRequest(), new VulnerableResponse());
    }
}
