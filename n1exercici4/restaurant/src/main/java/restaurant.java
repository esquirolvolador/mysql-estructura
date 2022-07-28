
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.Iterator;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;


public class restaurant {

  public static void main(String[] args) {

    ConnectionString connectionString = new ConnectionString("mongodb+srv://esquirolvolador" + ":Sedamisha5" + "@cluster0.fgdxy6l.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("restaurant");


    //Escriu una consulta per mostrar tots els documents en la col·lecció Restaurants.
    MongoCollection<Document> collection = database.getCollection("restaurant");
    FindIterable<Document> consulta1 = collection.find();
    iteradorFunc(consulta1);

    //Escriu una consulta per mostrar el restaurant_id, name, borough i cuisine per tots els documents en la col·lecció Restaurants.

    FindIterable<Document> consulta2 = collection.find().projection(include(
            "restaurant_id", "name", "borough", "cuisine"));
    iteradorFunc(consulta2);


    //Escriu una consulta per mostrar el restaurant_id, name, borough i cuisine, però exclou el camp _id per tots els documents en la col·lecció Restaurants.
    FindIterable<Document> consulta3 = collection.find().projection(fields(include(
            "restaurant_id", "name", "borough", "cuisine"), excludeId()));
    iteradorFunc(consulta3);


    //Escriu una consulta per mostrar restaurant_id, name, borough i zip code, però exclou el camp _id per tots els documents en la col·lecció Restaurants.
    FindIterable<Document> consulta4 = collection.find().projection(fields(include(
            "restaurant_id", "name", "borough", "address.zipcode"), excludeId()));
     iteradorFunc(consulta4);

    //Escriu una consulta per mostrar tots els restaurants que estan en el Bronx.
    Document findDocument = new Document("borough", "Bronx");
    FindIterable<Document> consulta5 = collection.find(findDocument);
    iteradorFunc(consulta5);

    //Escriu una consulta per mostrar els primers 5 restaurants que estan en el Bronx.
    Document find5 = new Document("_id", 1);
    FindIterable<Document> consulta6 = collection.find(findDocument).sort(find5).limit(5);
     iteradorFunc(consulta6);

    //Escriu una consulta per mostrar el pròxim 5 restaurants després de saltar els primers 5 del Bronx.

    FindIterable<Document> consulta7 = collection.find(findDocument).sort(find5).limit(5).skip(5);
    iteradorFunc(consulta7);

    //Escriu una consulta per trobar els restaurants que tenen un score de més de 90
    FindIterable<Document> consulta8 = collection.find(Filters.gt("grades.score", 90 ));
    iteradorFunc(consulta8);


    //Escriu una consulta per trobar els restaurants que tenen un score de més de 80 però menys que 100.

    Bson consulta9 = Filters.and(Filters.lt("grades.score", 100), Filters.gt("grades.score", 80));
    collection.find(consulta9).forEach(doc -> System.out.println(doc.toJson()));

    //Escriu una consulta per trobar els restaurants que es localitzen en valor de latitud menys de -95.754168.

    FindIterable<Document> consulta10 = collection.find(Filters.lt("address.coord.0", -95.754168 ));
    iteradorFunc(consulta10);

    //Escriu una consulta de MongoDB per a trobar els restaurants que no preparen cap cuisine de 'American' i la seva qualificació és superior a 70 i latitud inferior a -65.754168.

    Bson consulta11 = Filters.and(Filters.gt("grades.score", 70),Filters.lt("address.coord.0",
            -65.754168), Filters.ne("cuisine", "American "));
    collection.find(consulta11).forEach(doc -> System.out.println(doc.toJson()));


    //Escriu una consulta per trobar els restaurants que no preparen cap cuisine de 'American' i van obtenir un punt de grau 'A' no pertany a Brooklyn. S'ha de mostrar el document segons la cuisine en ordre descendent.

    Bson consulta12 = Filters.and(Filters.ne("cuisine", "American "),Filters.eq("grades.grade",
            "A"), Filters.ne("borough", "Brooklyn"));
    collection.find(consulta12).sort(descending("cuisine")).forEach(doc -> System.out.println(doc.toJson()));


    //Escriu una consulta per trobar el restaurant_id, name, borough i cuisine per a aquells restaurants que contenen 'Wil' com les tres primeres lletres en el seu nom.

    Bson consulta13 = Filters.regex("name", "^Wil");
    collection.find(consulta13).projection(include("restaurant_id", "name", "borough","cuisine")).forEach(doc -> System.out.println(doc.toJson()));;


    //Escriu una consulta per trobar el restaurant_id, name, borough i cuisine per a aquells restaurants que contenen 'ces' com les últimes tres lletres en el seu nom.
    Bson consulta14 = Filters.regex("name", "ces$");
    collection.find(consulta14).projection(include("restaurant_id", "name", "borough","cuisine")).forEach(doc -> System.out.println(doc.toJson()));;

    //Escriu una consulta per trobar el restaurant_id, name, borough i cuisine per a aquells restaurants que contenen 'Reg' com tres lletres en algun lloc en el seu nom.
    Bson consulta15 = Filters.regex("name", "Reg");
   collection.find(consulta15).projection(include("restaurant_id", "name", "borough","cuisine")).forEach(doc -> System.out.println(doc.toJson()));;


    //Escriu una consulta per trobar els restaurants que pertanyen al Bronx i van preparar qualsevol plat americà o xinès.
    Bson consulta16 = Filters.and( Filters.eq("borough", "Bronx"), Filters.or(Filters.eq("cuisine", "American "),Filters.eq("cuisine",
            "Chinese")));
   collection.find(consulta16).sort(descending("cuisine")).forEach(doc -> System.out.println(doc.toJson()));

 //Escriu una consulta per saber tant si totes les direccions contenen el carrer o no.

    FindIterable<Document> consulta17 = collection.find(exists("address.street", false));
   iteradorFunc(consulta17);

   //Escriu una consulta per trobar el restaurant_id, name, borough i cuisine per a aquells restaurants que no pertanyen a Staten Island o Queens o Bronx o Brooklyn.
    Bson consulta18 = Filters.and( Filters.ne("borough", "Bronx"),
            Filters.ne("borough", "Brooklyn"), Filters.ne("borough", "Queens"), Filters.ne("borough", "Staten Island"));
    collection.find(consulta18).projection(include("restaurant_id", "name", "borough","cuisine")).forEach(doc -> System.out.println(doc.toJson()));


  }


  public static void iteradorFunc(FindIterable<Document> docIteration) {

    Iterator it1 = docIteration.iterator();
    while (it1.hasNext()) {
      System.out.println(it1.next());
    }

  }
}