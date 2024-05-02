package router;

import java.util.HashMap;

public class Router {
    private static final HashMap<String, ControllerFunction> routes = new HashMap<>();
    public static String current;

    public static void add(String path, ControllerFunction function) {
        routes.put(path, function);
    }
    public static void get(String path) {
        routes.get(path).call("");
        current = path;
    }
    public static void get(String path,String arg) {

     try {
         routes.get(path).call(arg);
     }catch (Exception e) {
         System.out.println(e.getMessage() + " " + path);
     }
    }
}
