package org.example.spring.scoped;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new_api")
public class StatusController
{
    private final ScopedValue<StringBuilder> buffer_out = ScopedValue.newInstance();
    private final ScopedValue<String> data = ScopedValue.newInstance();

    @PostMapping(value = "/data_call", produces = MediaType.TEXT_HTML_VALUE)
    public String data(@RequestBody String input)
    {
        return ScopedValue.where(data, input)
            .call(data::get);
    }

    @PostMapping(value = "/buffer_status", produces = MediaType.TEXT_HTML_VALUE)
    public String status(@RequestBody String input)
    {
        var buffer = new StringBuilder();
        ScopedValue.where(buffer_out, buffer)
            .run(() -> buffer_out.get().append(input));
        return !buffer.toString().isEmpty()
            ? "OK" : "Ignored";
    }
}
