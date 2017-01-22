package com.mongodb.courses.homework;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.m101j.util.Helpers.printJson;

/**
 * Created by mchem on 1/21/2017.
 */
public class homework2_3 {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("students");
        MongoCollection<Document> collection = database.getCollection("grades");

        Bson filter = eq("type", "homework");
        Bson sort = ascending("student_id", "score");

        MongoCursor<Document> cursor = collection.find(filter)
                                                 .sort(sort)
                                                 .iterator();

        try {
            while (cursor.hasNext()) {
                Document doc1 = cursor.next();
                Document doc2 = cursor.next();

                printJson(doc1);
                collection.deleteOne(eq("_id", doc1.getObjectId("_id")));
            }
        } finally {
            cursor.close();
        }
    }
}
