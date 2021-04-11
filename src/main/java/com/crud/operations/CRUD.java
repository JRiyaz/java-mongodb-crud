package com.crud.operations;

import com.crud.database.Connect;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class CRUD {

    public static void main(String[] args) {

//        Getting Database. By default it will take database name in database.properties file
//        otherwise pass the database name to connect to it.
        final var database = Connect.toDB(null);

//        Create Collection
//        database.createCollection("java");

//        Print the list of connection
        final var collections = database.listCollectionNames();

        for (String collection : collections)
            System.out.println(collection);

//        Drop Collection
//        database.getCollection("java")
//                .drop();

        final var books = database.getCollection("books");


//        Insert one record into collection
        /*final var date = LocalDateTime.now();

        var doc1 = new Document();
        doc1.append("title", "Book Six");
        doc1.append("author", "Java");
        var category = new ArrayList<String>(Arrays.asList("Family", "Comedy"));
        doc1.append("category", category);
        doc1.append("body", "This is the body");
        doc1.append("pages", 123);
        doc1.append("date", date);

        var comments = new ArrayList<Document>();

        var sub_doc1 = new Document();
        sub_doc1.append("user", "User One");
        sub_doc1.append("comment", "This is Comment");
        sub_doc1.append("date", date);

        var sub_doc2 = new Document();
        sub_doc2.append("user", "User Two");
        sub_doc2.append("comment", "This is Comment");
        sub_doc2.append("date", date);

        var comment = new ArrayList<Document>();

        var sub2_doc1 = new Document();
        sub2_doc1.append("user", "User One");
        sub2_doc1.append("comment", "This is Comment");
        sub2_doc1.append("date", date);

        var sub2_doc2 = new Document();
        sub2_doc2.append("user", "User Two");
        sub2_doc2.append("comment", "This is comment");
        sub2_doc2.append("date", date);

        comment.add(sub2_doc1);
        comment.add(sub2_doc2);

        sub_doc2.append("comment", comment);

        comments.add(sub_doc1);
        comments.add(sub_doc2);

        doc1.append("comments", comments);

        books.insertOne(doc1);*/

//        Printing the records

        /*final var iterator = books.find().iterator();

        while (iterator.hasNext()) {

            System.out.println();
            final var book = new ArrayList<>(iterator.next().values());

            System.out.println("_id : " + book.get(0));
            System.out.println("title : " + book.get(1));
            System.out.println("author : " + book.get(2));
            System.out.println("category : " + book.get(3));
            System.out.println("body : " + book.get(4));
            System.out.println("pages : " + book.get(5));
            System.out.println("date : " + book.get(6));
            System.out.println("comments : ");
            var comments = new ArrayList<Document>((Collection<? extends Document>) book.get(7));

            for (Document comment : comments) {

                System.out.println("    user : " + comment.get("user"));
                System.out.println("    date : " + comment.get("date"));

                final var sub_comment = comment.get("comment");

                if (sub_comment instanceof String conts)
                    System.out.println("    comment : " + conts);
                else {
                    final var sub_comments = new ArrayList<Document>((Collection<? extends Document>) sub_comment);
                    System.out.println("    comment : ");
                    for (Document subComment : sub_comments) {
                        System.out.println("        user : " + subComment.get("user"));
                        System.out.println("        date : " + subComment.get("date"));
                        System.out.println("        comment : " + subComment.get("comment"));
                    }
                }
            }
        }*/

//        Updating record
        final var updateResult = books.updateOne(new Document("title", "Book Six"),
                new Document("$set", new Document("author", "Cloud")));

        System.out.println(updateResult);

//        Finding the record using title
        /*final Bson bson = Filters.eq("title", "Book Six");
        final var documents = books.find(bson);
        final var first = documents.first();
        System.out.println(first);*/

//        Inserting dummy record to delete
        books.insertOne(new Document("title", "Book Seven"));

        final var deleteResult = books.deleteOne(Filters.eq("title", "Book Seven"));

        System.out.println(deleteResult);

        Connect.getMongoClient().close();
    }
}
