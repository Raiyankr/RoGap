package Use_Cases;

import Entities.MeleeEnemy;
import Entities.RangedEnemy;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * This class manages all enemies of the game. It contains array lists of all Ranged monsters and Melee monsters,
 * allowing for all monsters to be accessed updated at once.
 */
public class EnemyManagerHandler implements CreateEnemyInputBoundary{

    private HashMap<String, MeleeEnemy> meleeEnemies;
    private HashMap<String, RangedEnemy> rangedEnemies;
    private int enemyCount = 0;

    /**
     * Creates new enemies, spawns them on the map, and adds each one to their respective array list.
     */
    public void createEnemies(int xDelta, int yDelta) {
        meleeEnemies = new HashMap<>();
        meleeEnemies.put("MeleeEnemy1", EnemySpawnInteractor.spawnMelee("meleeEnemy1", xDelta, yDelta));

        rangedEnemies = new HashMap<>();
        rangedEnemies.put("RangedEnemy1", EnemySpawnInteractor.spawnRanged("RangedEnemy1", xDelta, yDelta));
        enemyCount = meleeEnemies.size() + rangedEnemies.size();
    }

    public void removeEnemy(String enemyName){
        boolean check = RemoveEnemyInteractor.removeEnemy(enemyName, meleeEnemies, rangedEnemies);
        if (check){
            updateEnemyCount();
        }
    }

    private void updateEnemyCount(){
        enemyCount -= 1;
    }


    public int getEnemyCount(){
        return this.enemyCount;
    }

    /**
     * Returns a list of "spawned" enemies or null if no enemies were spawned.
     */
    public ArrayList<ArrayList> getEnemies() {

        ArrayList<ArrayList> enemyInfoList = new ArrayList<>();

        for (MeleeEnemy m : meleeEnemies.values()){
            ArrayList enemyInfo = new ArrayList<>();
            enemyInfo.add(m.getAnimations());
            enemyInfo.add(m.getVisualX());
            enemyInfo.add(m.getVisualY());

            enemyInfoList.add(enemyInfo);
        }

        for (RangedEnemy r : rangedEnemies.values()){
            ArrayList enemyInfo = new ArrayList<>();

            enemyInfo.add(r.getAnimations());
            enemyInfo.add(r.getVisualX());
            enemyInfo.add(r.getVisualY());

            enemyInfoList.add(enemyInfo);
        }

        return enemyInfoList;
    }
    public void updateEnemies(int velX, int velY) {
        for (MeleeEnemy m : meleeEnemies.values()){
            m.changeHelperX(-velX);
            m.changeVisualX(-velX);
            m.changeHelperY(-velY);
            m.changeVisualY(-velY);
        }

/*        for (RangedEnemy r : rangedEnemies.values()){
            r.changeHelperX(velX);
            r.changeHelperX(velX);
            r.changeVisualX(velY);
            r.changeVisualY(velY);
        }*/
    }
    public ArrayList<String> getEnemyID(){
        ArrayList<String> enemyNameList = new ArrayList<>();

        enemyNameList.addAll(meleeEnemies.keySet());
        enemyNameList.addAll(rangedEnemies.keySet());

        return enemyNameList;
    }

}