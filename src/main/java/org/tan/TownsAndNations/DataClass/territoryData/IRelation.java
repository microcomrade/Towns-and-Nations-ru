package org.tan.TownsAndNations.DataClass.territoryData;

import org.tan.TownsAndNations.DataClass.TownRelations;
import org.tan.TownsAndNations.enums.TownRelation;

public interface IRelation {

    TownRelations getRelations();
    void addTownRelation(TownRelation relation, ITerritoryData territoryData);
    void addTownRelation(TownRelation relation, String territoryID);
    void removeTownRelation(TownRelation relation, ITerritoryData territoryData);
    void removeTownRelation(TownRelation relation, String territoryID);
    TownRelation getRelationWith(TownData townData);
    TownRelation getRelationWith(String townID);

}
