
import java.net.*;
import java.io.*;


public class test{
    public static void main(String[] args) throws Exception {
        URL request = new URL("https://jsonplaceholder.typicode.com/todos/1");
        URLConnection yc = request.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));

        String json;
        while ((json = in.readLine()) != null) 
            System.out.println(json);
        in.close();

        // JSONParser parser = new JSONParser();
        // JSONObject jsonObject = (JSONObject)parser.parse(json);
        // String title = (String) jsonObject.get("title");
        
        // System.out.println(title + " " + "Is the title.");
    }
}