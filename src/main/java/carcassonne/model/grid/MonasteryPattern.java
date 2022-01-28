/**
 *
 */
package carcassonne.model.grid;

import static carcassonne.model.grid.GridDirection.CENTER;
import static carcassonne.model.terrain.TerrainType.MONASTERY;

import java.util.List;

/**
 * This class represents a specific kind of grid pattern, the grid patterns for the terrain type MONASTERY.
 * @author Timur Saglam
 */
public class MonasteryPattern extends GridPattern {

    /**
     * Simple constructor that creates the pattern.
     * @param spot is the starting spot of the pattern, containing a monastery tile.
     * @param grid is the grid the pattern is created from.
     */
    public MonasteryPattern(GridSpot spot) {
        super(MONASTERY, 1);
        if (spot.getTile().getTerrain(CENTER) != MONASTERY) {
            throw new IllegalArgumentException("Can't create monastery pattern from non monastery tile");
        }
        buildPattern(spot);
    }

    private void buildPattern(GridSpot monasterySpot) {
        List<GridSpot> neighbors = monasterySpot.getGrid().getNeighbors(monasterySpot, false, GridDirection.neighbors());
        add(monasterySpot); // add monastery
        monasterySpot.setTag(CENTER, this);
        neighbors.forEach(it -> containedSpots.add(it));
        if (neighbors.size() == GridDirection.neighbors().size()) {
            complete = true;
        }
    }
}
