import DAO.Sql2oHeroDAO;
import DAO.Sql2oSquadDAO;
import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        String connectionString = "jdbc:postgresql://localhost:5432/herosquads";
        Connection con;
        Sql2o sql2o = new Sql2o(connectionString, "prince", "prince12");
        Sql2oSquadDAO squadDAO = new Sql2oSquadDAO(sql2o);
        Sql2oHeroDAO heroDAO = new Sql2oHeroDAO(sql2o);
        Map<String, Object> model = new HashMap<>();

        List<String> myStrings = new ArrayList<>();

        staticFileLocation("/public");


        get("/", (req, res) -> {
            model.put("squads", squadDAO.getAllSquads());
            model.put("heroes", heroDAO.getAllHeroes());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addsquad", (req, res) -> {
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addsquad", (req, res) -> {
            String name = req.queryParams("name");
            String cause = req.queryParams("cause");
            int size = Integer.parseInt(req.queryParams("size"));
            Squad newSquad = new Squad(name, cause, size);
            squadDAO.addSquad(newSquad);
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/addhero", (req, res) -> {
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/addhero", (req, res) -> {
            String name = req.queryParams("name");
            String power = req.queryParams("power");
            String weakness = req.queryParams("weakness");
            int age = Integer.parseInt(req.queryParams("age"));
            int squadId = Integer.parseInt(req.queryParams("squad"));
            Hero newHero = new Hero(name, power, weakness, age, squadId);
            heroDAO.addHero(newHero);
            model.put("squads", squadDAO.getAllSquads());
            model.put("heroes", heroDAO.getAllHeroes());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            Hero hero = heroDAO.getHeroById(id);
            Squad squad = squadDAO.getSquadById(hero.getSquadId());
            model.put("hero", heroDAO.getHeroById(id));
            model.put("squad", squad);
            return new ModelAndView(model, "hero-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            model.put("heroes", squadDAO.getSquadHeroesById(id));
            model.put("squad", squadDAO.getSquadById(id));
            return new ModelAndView(model, "squad-details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deletehero/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            heroDAO.deleteHeroById(id);
            model.put("heroes", heroDAO.getAllHeroes());
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/deletesquad/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            squadDAO.deleteSquadById(id);
            squadDAO.deleteHeroesInSquad(id);
            model.put("heroes", heroDAO.getAllHeroes());
            model.put("squads", squadDAO.getAllSquads());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
