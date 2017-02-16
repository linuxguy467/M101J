package com.mongodb.week5;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.m101j.util.*;
import org.bson.*;
import org.bson.conversions.*;
import org.bson.types.*;

import java.util.ArrayList;
import java.util.*;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

/**
 *
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

        System.out.flush();

        List<Document> documents = synchronizedList(new ArrayList<>());

        documents.add(new Document("_id", new ObjectId())
                .append("a", 1)
                .append("b", 2)
                .append("c", 3));
        documents.add(new Document("_id", new ObjectId())
                .append("a", 5)
                .append("b", 7)
                .append("c", 6)
                .append("bool", false));
        documents.add(new Document("_id", new ObjectId())
                .append("a", 7)
                .append("b", 9)
                .append("c", 11)
                .append("d", "Maybe")
                .append("bool", true));

        List<String> strings =
                documents.parallelStream()
                        .filter(document -> document.getInteger("b") > 5)
                        .sorted(comparing(Document::size).reversed())
                        .map(Helpers::toJson)
                        .collect(toList());
        strings.forEach(System.out::println);

    }
}
