package DAO;

import models.Hero;
import models.Squad;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSquadDAO implements SquadDAO {


    private final Sql2o sql2o;

    public Sql2oSquadDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Squad> getAllSquads() {
        String sql = "SELECT * FROM squads";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Squad.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void addSquad(Squad squad) {
        String sql = "INSERT INTO squads (squadName, squadMission, squadSize) VALUES (:squadName, :squadMission, :squadSize)";
        try(Connection con= sql2o.open()){
            int id = (int)  con.createQuery(sql, true)
                    .bind(squad)
                    .executeUpdate()
                    .getKey();
            squad.setId(id);
        }catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Squad getSquadById(int id) {
        String sql = "SELECT * FROM squads WHERE id=:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Squad.class);
        }catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }

    }

    @Override
    public List<Hero> getSquadHeroesById(int id) {
        String sql = "SELECT * FROM heroes WHERE squadId=:id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetch(Hero.class);
        }catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void deleteSquadById(int id) {
        String sql = "DELETE FROM squads WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteHeroesInSquad(int id) {
        String sql = "DELETE FROM heroes WHERE squadId=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
