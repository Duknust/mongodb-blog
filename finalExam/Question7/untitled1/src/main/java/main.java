//created by duknust
//find in https://github.com/Duknust

import com.mongodb.*;
import com.mongodb.MongoClient;

import java.io.IOException;
import java.net.UnknownHostException;

public class main {
    public static void main(String[] args) throws IOException {
        MongoClient c =  new MongoClient(new MongoClientURI("mongodb://localhost"));
        DB db = c.getDB("final7");
        int i =0;
        DBCollection albuns = db.getCollection("albums");
        DBCollection images = db.getCollection("images");
        
        DBCursor cursor = images.find();
        cursor.next();
        
        while (cursor.hasNext()){
            Object id = cursor.curr().get("_id");
            DBCursor cursoralbum = albuns.find(new BasicDBObject("images", id));
            if(!cursoralbum.hasNext()){
                images.remove(new BasicDBObject("_id", id));
            }
            cursor.next();
        }
        System.out.println("the end\n");
    }
}
