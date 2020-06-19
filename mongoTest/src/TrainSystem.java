import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Iterator;
import java.util.Scanner;

public class TrainSystem {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter any operation from this list (save,load,update,delete, drop): ");
        String userInput = input.nextLine();

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://trainsystem:trainsystem@mycluster-hx5eq.mongodb.net/myDatabase?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("myDatabase");
        MongoCollection<Document> myCollection = database.getCollection("myCollection");

        switch (userInput){
            case "save":
                save(myCollection);
                break;
            case "load":
                load(myCollection);
                break;
            case "update":
                update(myCollection);
                break;
            case "delete":
                delete(myCollection);
            case "drop":
                drop(myCollection);
        }
    }

    private static void drop(MongoCollection<Document> myCollection) {
        myCollection.drop();
        System.out.println("Collection Successfully Dropped!!!");
    }

    private static void save(MongoCollection<Document> myCollection){
        Document myDocument = new Document("firstDocument","mongoDocument")
        .append("Name","Nazhim").append("Age",18);

        myCollection.insertOne(myDocument);

        System.out.println("Document Successfully Saved!!!");

    }

    private static void load(MongoCollection<Document> myCollection){
        System.out.println("Document Successfully Loading!!!");

        for (Document document : myCollection.find()) {
            System.out.println(document);
        }
    }
    private static void update(MongoCollection<Document> myCollection) {
        myCollection.updateMany(Filters.eq("Age",20), Updates.set("Age",99));
        System.out.println("Data Successfully Updated!!!");
    }

    private static void delete(MongoCollection<Document> myCollection) {
        myCollection.deleteOne(Filters.eq("firstDocument","mongoDocument"));
        System.out.println("Document Successfully deleted!!!");
    }


}
