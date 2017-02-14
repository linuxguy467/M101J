package com.mongodb.m101j.util;

import org.bson.*;
import org.bson.codecs.*;
import org.bson.json.*;

import java.io.*;

/**
 * Created by mchem on 1/19/2017.
 */
public class Helpers {
    public static void printJson(Document document){
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                                               new JsonWriterSettings(JsonMode.SHELL, true));
        new DocumentCodec().encode(jsonWriter, document,
                                    EncoderContext.builder()
                                                .isEncodingCollectibleDocument(true)
                                                .build());
        System.out.println(jsonWriter.getWriter());
        //System.out.println();
        System.out.flush();
    }

    public static String toJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                new JsonWriterSettings(JsonMode.SHELL, true));
        new DocumentCodec().encode(jsonWriter, document,
                EncoderContext.builder()
                        .isEncodingCollectibleDocument(true)
                        .build());

        return jsonWriter.getWriter().toString();
    }

    public static void printJson(BsonDocument document){
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                new JsonWriterSettings(JsonMode.SHELL, true));
        new BsonDocumentCodec().encode(jsonWriter, document,
                EncoderContext.builder()
                        .isEncodingCollectibleDocument(true)
                        .build());
        System.out.println(jsonWriter.getWriter());
        //System.out.println();
        System.out.flush();
    }
}
