
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.types.ObjectId;
import org.bson.Document;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;


public class pizzeria {

  public static void main(String[] args) {

    ConnectionString connectionString = new ConnectionString("mongodb+srv://esquirolvolador" + ":Sedamisha5" + "@cluster0.fgdxy6l.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("Pizzeria");

    Random rand = new Random();

    Document client = new Document("_id", new ObjectId());
    client.append("nom", "Arianna")
            .append("cognoms", "Puig, Torrero")
            .append("direccio", new Document("carrer", "Passeig Fabra i Puig")
                    .append("numero", 176)
                    .append("pis", 4)
                    .append("porta", 3))
            .append("codi_postal", "08016")
            .append("localitat", Arrays.asList(new Document("_id", new ObjectId()).append("nom",
                            "Barcelona")))
            .append("provincia", Arrays.asList(new Document("_id", new ObjectId()).append("nom",
                            "Barcelona")))
            .append("telefon", 620007699)
            .append("comandes", Arrays.asList("", ""));

    database.getCollection("client").insertOne(client);


    Document comanda = new Document("_id", new ObjectId());
    comanda.append("data_hora", "27/10/2020 15:30")
            .append("client", "")
            .append("botiga", "")
            .append("tipus_comanda", Arrays.asList(new Document("domicili", new ObjectId()).append(
                    "nom_repartidor", "Mireia").append("data_hora", "12/08/2022 13:50"),
    (new Document("recollida", new ObjectId()))))
            .append("quantitat_productes", Arrays.asList(new Document("hamburguesa",
                            new Document("_id", new ObjectId())
                                    .append("nom", "hamburguesa amb formatge")
                                    .append("descripcio", "emciam, formatge, hamburguesa")
                                    .append("imatge", "")
                                    .append("preu", 8)),
                    new Document("beguda", new Document("_id", new ObjectId())
                            .append("nom", "Coca-Cola " +
                                    "Zero")
                            .append("descripcio", "cola, zero sucre")
                            .append("imatge", "")
                            .append("preu", 2.50)),
                    new Document("pizza", new Document("_id", new ObjectId())
                            .append("nom", "Trufada")
                            .append("categoria", (new Document("_id", new ObjectId())
                                    .append("nom", "premium")))
                            .append("descripcio", "mozzarella, trufa, crema")
                            .append("imatge", "")
                            .append("preu", 13.50))))
            .append("preu_total", 24);

    database.getCollection("comanda").insertOne(comanda);

   Document botiga = new Document("_id", new ObjectId());
    botiga.append("direccio", new Document("carrer", "Passeig Fabra i Puig")
                    .append("numero", 176)
                    .append("pis", 4)
                    .append("porta", 3))
            .append("codi_postal", "08016")
            .append("localitat", Arrays.asList(new Document("_id", new ObjectId()).append("nom",
                    "Barcelona")))
            .append("provincia", Arrays.asList(new Document("_id", new ObjectId()).append("nom",
                    "Barcelona")))
            .append("comandes", Arrays.asList("1234", "1235"))
            .append("empletas", Arrays.asList("Sandra", "Josep"));

    database.getCollection("botiga").insertOne(botiga);

    Document empleat = new Document("_id", new ObjectId());
    empleat.append("nom", "Mireia")
            .append("cognoms", "Riu, Soler")
            .append("NIF", "37310447L")
            .append("telefon", 620006455)
            .append("tipus", Arrays.asList("repartidor", "cuiner"))
            .append("botiga", "_id");

    database.getCollection("empleat").insertOne(empleat);

    MongoCollection<Document> collection = database.getCollection("client");
    FindIterable<Document> iterDoc = collection.find();
    Iterator it = iterDoc.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());


      System.out.println(database.getCollection("ulleres").countDocuments());
      System.out.println(database.getCollection("client").countDocuments());


    }
  }
}
