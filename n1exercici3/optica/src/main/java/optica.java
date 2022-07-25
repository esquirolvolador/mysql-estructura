
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.types.ObjectId;
import org.bson.Document;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;


public class optica {

  public static void main(String[] args) {

  ConnectionString connectionString = new ConnectionString("mongodb+srv://esquirolvolador" + ":Sedamisha5" + "@cluster0.fgdxy6l.mongodb.net/?retryWrites=true&w=majority");
  MongoClientSettings settings = MongoClientSettings.builder()
          .applyConnectionString(connectionString)
          .build();
  MongoClient mongoClient = MongoClients.create(settings);
  MongoDatabase database = mongoClient.getDatabase("Optica_cul_ampolla");


    Random rand = new Random();

    Document proveidor = new Document("_id", new ObjectId());
    proveidor.append("nom", "Carroussel")
            .append("direccio", new Document("carrer", "Mar i Muntanya")
                    .append("numero", 40)
                    .append("pis", 1)
                    .append("porta", 3)
                    .append("ciutat",  "Barcelona")
                    .append("codi_postal", "08030")
                    .append("pais", "Espanya"))
            .append("telefon", 658652653)
            .append("fax", 934785123)
            .append("nif", "45544815P");

  database.getCollection("proveidor").insertOne(proveidor);

    Document client = new Document("_id", new ObjectId());
    client.append("nom", "Arianna")
            .append("direccio", new Document("carrer", "Passeig Fabra i Puig")
                    .append("numero", 176)
                    .append("pis", 4)
                    .append("porta", 3)
                    .append("ciutat",  "Barcelona")
                    .append("codi_postal", "08016")
                    .append("pais", "Espanya"))
            .append("telefon", 620007699)
            .append("correu_electronic", "esquirol.volador@me.com")
            .append("data_registre", "01/01/2022")
            .append("recomenat_per", "Carroussel")
            .append("venedor", "Mireia");


    database.getCollection("client").insertOne(client);

    Document ulleres = new Document("_id", new ObjectId());
    ulleres.append("marca", "Ray-Ban")
            .append("graduacio", "Ray-Ban")
            .append("color_muntura", "negre")
            .append("color_vidre2", "transparent")
            .append("color_vidre2", "transparent")
            .append("preu", 275.5)
            .append("nom_proveidor", "Carroussel")
            .append("muntura", Arrays.asList("flotant", "pasta", "metall"));

    database.getCollection("ulleres").insertOne(ulleres);

    MongoCollection<Document> collection = database.getCollection("proveidor");
    FindIterable<Document> iterDoc = collection.find();
    Iterator it = iterDoc.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());


  System.out.println(database.getCollection("ulleres").countDocuments());
    System.out.println(database.getCollection("client").countDocuments());


}
}
}
