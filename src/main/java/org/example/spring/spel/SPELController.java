package org.example.spring.spel;

import org.intellij.lang.annotations.Language;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spel")
public class SPELController
{
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private static final StandardEvaluationContext CONTEXT = new StandardEvaluationContext();

    @Language("html")
    @SuppressWarnings("HtmlUnknownTarget")
    private static final String TEST_FORM = """
        <!DOCTYPE html>
        <html lang="en">
        <body>
            <form method="post" action="/spel/parse">
                <input type="text" name="expression">
                <input type="submit">
            </form>
        </body>
        </html>
        """;

    @GetMapping("/")
    public String form()
    {
        return TEST_FORM;
    }

    @PostMapping(path = "/parse", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String parse(@RequestParam String expression)
    {
        Expression parsedExpression = PARSER.parseExpression(expression); // CWE 95
        System.out.println(parsedExpression.getExpressionString());
        return "OK";
    }

    @PostMapping(path = "/parse_context", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String parse_context(@RequestParam String expression)
    {
        Expression parsedExpression = PARSER.parseExpression(expression, null); // CWE 95
        System.out.println(parsedExpression.getExpressionString());
        return "OK";
    }

    @PostMapping(path = "/set_value", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String set_value(@RequestParam String expression)
    {
        Expression parsedExpression = PARSER.parseExpression("1 + 1");
        parsedExpression.setValue((Object) null, expression); // CWE 95
        System.out.println(parsedExpression.getExpressionString());
        return "OK";
    }

    @PostMapping(path = "/set_value_context", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String set_value_context(@RequestParam String expression)
    {
        Expression parsedExpression = PARSER.parseExpression("1 + 1");
        parsedExpression.setValue(CONTEXT, expression); // CWE 95
        System.out.println(parsedExpression.getExpressionString());
        return "OK";
    }

    @PostMapping(path = "/set_value_root_context", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String set_value_root_context(@RequestParam String expression)
    {
        Expression parsedExpression = PARSER.parseExpression("1 + 1");
        parsedExpression.setValue(CONTEXT, null, expression); // CWE 95
        System.out.println(parsedExpression.getExpressionString());
        return "OK";
    }

    @PostMapping(value = "/echo", produces = MediaType.TEXT_HTML_VALUE)
    public String echo(@RequestBody String input, @RequestBody String[] args)
    {
        return input;
    }
}
