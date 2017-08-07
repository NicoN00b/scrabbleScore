import scrabble.Scrabble;

import spark.ModelAndView;
//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("I can validate your Scrabble score");
//        System.out.println("Enter your word:");

        staticFileLocation( "/public");

        get("/", (request, response) ->
        {   Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "scrabble.hbs");
        }, new HandlebarsTemplateEngine());

        get("/score", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String inputtedWord = request.queryParams("inputtedWord");

//        try {
            //String inputtedWord = bufferedReader.readLine();
            Scrabble newScrabble = new Scrabble();
            int scrabbleResult = newScrabble.calculateScore(inputtedWord);
//            System.out.println(scrabbleResult);

//        }catch (IOException e){
//            e.printStackTrace();
//        }
            model.put("inputtedWord", scrabbleResult);
            return new ModelAndView(model, "score.hbs");

        }, new HandlebarsTemplateEngine());
    }
}
