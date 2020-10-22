package DAO;

import models.Hero;
import models.Squad;

import java.util.List;

public interface SquadDAO {

    List<Squad> getAllSquads();

    void addSquad(Squad squad);

    Squad getSquadById(int id);

    List<Hero> getSquadHeroesById(int id);

    void deleteSquadById(int id);

    void deleteHeroesInSquad(int id);
}