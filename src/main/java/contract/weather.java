package contract;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class weather
{
    public static Map<String,Object> jsonToMap(String str)
    {
        Map<String,Object> map= new Gson().fromJson(str,new TypeToken<HashMap<String,Object>>() {}.getType());
        return map;
    }
    public static void main(String[] args)
    {
        String API_KEY="ee6c737dd3fe415ab6b841cf3d585ad3";
        String LOCATION="Raleigh,NC";
        //String urlString ="http://api.openweathermap.org/data/2.5/weather?="+LOCATION+"&appid="+API_KEY+"&units=metric";
        String urlString ="https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
        try{
            StringBuilder result = new StringBuilder();
            URL url=new URL(urlString);
            URLConnection conn=url.openConnection();
            BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=rd.readLine())!=null)
            {
                result.append(line);
            }
            rd.close();
            System.out.println(result);
            Map<String,Object> respMap=jsonToMap(result.toString());
            Map<String,Object> mainMap=jsonToMap(respMap.get("main").toString());
            Map<String,Object> windMap=jsonToMap(respMap.get("wind").toString());

            System.out.println("Current temperature:" + mainMap.get("temp"));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}