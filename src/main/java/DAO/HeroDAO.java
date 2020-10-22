package DAO;

import models.Hero;
import models.Squad;

import java.util.List;

public interface HeroDAO {

    List<Hero> getAllHeroes();

    void addHero(Hero hero);

    Hero getHeroById(int id);

    void deleteHeroById(int id);
}
