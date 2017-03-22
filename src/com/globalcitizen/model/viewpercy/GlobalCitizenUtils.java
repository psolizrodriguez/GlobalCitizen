
package com.globalcitizen.model.viewpercy;

import com.globalcitizen.model.characters.Creature;
import com.globalcitizen.model.characters.Point;
import com.globalcitizen.model.characters.Street;

public class GlobalCitizenUtils {

	public static Point getNextLinePoint(Street street, Creature creature) {
		Double endX = creature.getCurrentPosition().getX() + 1 * Math.cos(Math.toRadians(street.getDirection()));
		Double endY = creature.getCurrentPosition().getY() + 1 * Math.sin(Math.toRadians(street.getDirection()));
		return new Point(endX.intValue(), endY.intValue());
	}
}
