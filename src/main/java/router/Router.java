package router;

import java.util.HashMap;

public class Router {
    private static final HashMap<String, ControllerFunction> routes = new HashMap<>();

    public static void add(String path, ControllerFunction function) {
        routes.put(path, function);
    }
    public static void get(String path) {
        routes.get(path).call("");
    }
    public static void get(String path,String arg) {

        routes.get(path).call(arg);
    }
}
