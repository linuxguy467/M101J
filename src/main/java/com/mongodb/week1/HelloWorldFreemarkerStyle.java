package com.mongodb.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mchem on 1/11/2017.
 */
public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(
                HelloWorldFreemarkerStyle.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("freemarker/hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Freemarker");

            helloTemplate.process(helloMap, writer);

            System.out.println(writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}