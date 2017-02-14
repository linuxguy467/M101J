package com.mongodb.week5;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.m101j.util.Helpers;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.gte;
import static java.util.Arrays.asList;
import static java.util.Collections.synchronizedList;

/**
 * Created by mchem on 2/14/2017.
 */
public class ZipCodeAggregationTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("zipcodes");

        List<Bson> pipeline = asList(group("$state", sum("totalPop", "$pop")),
                match(gte("totalPop", 10000000)));

        List<Document> results = collection.aggregate(pipeline)
                .into(new ArrayList<>());

        results.forEach(document -> System.out.println(document.toJson()));

        List<Document> documents = synchronizedList(new ArrayList<>());

        documents.add(new Document("_id", new ObjectId())
                .append("a", 1)
                .append("b", 2)
                .append("c", 3));
        documents.add(new Document("_id", new ObjectId())
                .append("a", 5)
                .append("b", 4)
                .append("c", 6));
        documents.add(new Document("_id", new ObjectId())
                .append("a", 7)
                .append("b", 9)
                .append("c", 11));

        synchronized (documents) {
            Iterator<Document> iterator = documents.iterator();
            iterator.forEachRemaining(Helpers::printJson);
        }
    }
}
