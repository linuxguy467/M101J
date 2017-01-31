package com.mongodb.courses.homework;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.m101j.util.Helpers.printJson;

/**
 * Created by mchem on 1/21/2017.
 */
public class homework3_1 {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("school");
        MongoCollection<Document> collection = database.getCollection("students");

        Bson filter = eq("scores.type", "homework");
        Bson sort = ascending("_id", "scores.score");

        MongoCursor<Document> cursor = collection.find(filter)
                                                 .sort(sort)
                                                 .iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                //Document doc2 = cursor.next();

                List<Document> doublist = (List<Document>) doc.get("scores");

                for (int i=0;i<doublist.size();i++) {
                    if(doublist.get(i).containsValue("homework")){
                        if(doublist.get(i).getDouble("score") > doublist.get(i+1).getDouble("score")){
                            doublist.remove(i+1);
                        }else if(doublist.get(i).getDouble("score") < doublist.get(i+1).getDouble("score")){
                            doublist.remove(i);
                        }
                        break;
                    }
                }

                collection.updateOne(eq("_id", doc.getInteger("_id")), set("scores", doublist));
                printJson(doc);
            }
        } finally {
            cursor.close();
        }
    }
}
