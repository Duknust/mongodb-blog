import com.mongodb.*;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by duarteduarte on 14/01/15.
 */
public class main {
    public static void main(String[] args) {
        int i = 0;
        MongoClient client = null;
        try {
            client = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");
        DBObject tmp = null;
        DBObject min = null;

        try {
            for(i=0;i<200;i++){
                DBCursor lista=collection.find(new BasicDBObject("student_id",i).append("type","homework"));
                while(lista.hasNext()){
                    tmp = lista.next();
                    System.out.println("tmp "+tmp.get("score"));
                    if(min!=null){System.out.println("min " + min.get("score") + "\n");
                     if(Float.parseFloat(tmp.get("score").toString())<Float.parseFloat(min.get("score").toString())) {
                         min = tmp;
                     }}
                    else {
                        min=tmp;
                    }
                }
                collection.remove(min);
                    min=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
