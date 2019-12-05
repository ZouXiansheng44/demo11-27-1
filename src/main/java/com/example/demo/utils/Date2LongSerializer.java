package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

//时间长度转换类
public class Date2LongSerializer extends JsonSerializer<Date>
{
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException
    {
        jsonGenerator.writeNumber(date.getTime()/1000);//长整形用于精确到秒
    }
}
