import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;

class Requester {

  JSONObject getJson(String hostname, String port, String tag) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String url = "http://" + hostname + ":" + port + "/read.json?tag=" + tag;
    URL obj = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
    connection.setRequestProperty("Connection", "close");
    connection.setRequestMethod("GET");
    int responseCode = connection.getResponseCode();
//    System.out.println("\nSending 'GET' request to URL : " + url);
//    System.out.println("Response Code is: " + responseCode);
    BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuilder response = new StringBuilder();
    while ((inputLine = bufferedReader.readLine()) != null) {
      response.append(inputLine);
    }
    bufferedReader.close();
    JSONTokener tokener = new JSONTokener(response.toString());
    JSONObject jobj = new JSONObject(tokener);
    return jobj;
  }


  void setValue(String hostname, String port, String tag, float value) {
    String url = "http://" + hostname + ":" + port + "/write.json?tag="+tag+"&val="+value;
    try{
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestProperty("Connection", "close");
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();
      //System.out.println("\nWriting 'GET' request to URL : " + url);
      //System.out.println("Response Code : " + responseCode);
    }catch (IOException ex){
      System.out.println("Exception " + ex.getMessage());
    }
  }
}
