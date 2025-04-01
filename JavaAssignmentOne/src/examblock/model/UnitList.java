package examblock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection object for holding and managing `Subject` `Unit`s.
 */
public class UnitList {
    /**
     * list of units
     */
    private List<Unit> units;

    /**
     * Constructs an empty list of `Unit`s.
     */
    public UnitList() {
        units = new ArrayList<>();
    }

    /**
     * Adds a `Unit` to this list of `Unit`s.
     *
     * @param unit - the unit object being added to this list.
     */
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    /**
     * Removes a given `Unit` from the `UnitList`.
     *
     * @param unit - the unit to remove from this list.
     */
    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    /**
     * Get the first `Unit` with a matching `Subject` and `unitId`.
     *
     * @param title  - the `title` of the parent `Subject` of the `Unit` to be found.
     * @param unitId - the unit identifier of the `Subject` `Unit` to be found.
     * @return first `Unit` with a matching subject `title` and `unitId`, if it exists.
     * @throws IllegalStateException - throw an IllegalStateException if it can't find a matching
     *                               unit
     *                               as that indicates there is a misalignment of the executing
     *                               state and the complete list of possible units.
     */
    public Unit getUnit(String title, Character unitId) throws IllegalStateException {
        for (Unit unit : units) {
            if (unit.getSubject().getTitle().equals(title)
                    && unit.id().equals(unitId)) {
                return unit;
            }
        }
        throw new IllegalStateException("Unit with subject title '"
                + title
                + "' and unit ID '"
                + unitId
                + "' not found");
    }

    /**
     * Creates a new `List` holding `references` to all the `Unit`s managed by the `UnitList` and
     * returns it.
     *
     * @return a new `List` holding `references` to all the `Unit`s managed by the `UnitList`.
     */
    public List<Unit> all() {
        return new ArrayList<>(units);
    }

    /**
     * Returns detailed string representations of the contents of this unit list.
     *
     * @return detailed string representations of the contents of this unit list.
     */
    public String getFullDetail() {
        StringBuilder result = new StringBuilder("Unit List - "
                + units.size()
                + " unit(s)\n");

        for (int i = 0; i
                < units.size(); i++) {
            result.append(units.get(i).getFullDetail());
            if (i
                    < units.size()
                    - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Returns a string representation of the contents of the unit list
     *
     * @return a string representation of the contents of the unit list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i
                < units.size(); i++) {
            result.append(units.get(i).getSubject());
            result.append(": ");
            result.append(units.get(i).toString());
            if (i
                    < units.size()
                    - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
