package com.example.oamanageros.wx.config.xss;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 笑的心酸 - Red4Lion - mnmnmssd
 * @date 2021.12.21
 * 配置Xss过滤 防止Xss跨站脚本攻击
 */
public class XssHttpRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (!StrUtil.hasEmpty(value)) {
            value = HtmlUtil.filter(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                if (!StrUtil.hasEmpty(values[i])) {
                    values[i] = HtmlUtil.filter(values[i]);
                }
            }
        }

        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameters = super.getParameterMap();
        LinkedHashMap<String, String[]> map = new LinkedHashMap<>();
        if (parameters != null) {
            for (String key:parameters.keySet()){
                String[] values = parameters.get(key);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (!StrUtil.hasEmpty(values[i])) {
                            values[i] = HtmlUtil.filter(values[i]);
                        }
                    }
                    map.put(key,values);
                }
            }
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (!StrUtil.hasEmpty(value)) {
            value = HtmlUtil.filter(value);
        }
        return value;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        InputStream in = super.getInputStream();
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        String line = bufferedReader.readLine();
        while (line != null) {
            buffer.append(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();
        in.close();

        Map<String, Object> map = JSONUtil.parseObj(buffer.toString());
        Map<String, Object> result = new LinkedHashMap<>();
        for (String key: map.keySet()) {
            Object val = map.get(key);
            if (val instanceof String) {
                if (!StrUtil.hasEmpty(val.toString())) {
                    result.put(key, HtmlUtil.filter(val.toString()));
                }
            } else {
                result.put(key, val);
            }
        }

        String json = JSONUtil.toJsonStr(result);
        ByteArrayInputStream bs = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bs.read();
            }
        };
    }
}
