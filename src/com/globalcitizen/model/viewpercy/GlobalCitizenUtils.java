
package com.globalcitizen.model.viewpercy;

import java.awt.Point;
import java.awt.Rectangle;

import com.globalcitizen.model.characters.Car;
import com.globalcitizen.model.characters.Creature;
import com.globalcitizen.model.characters.Hero;
import com.globalcitizen.model.characters.Street;

public class GlobalCitizenUtils {

	public static Point getNextLinePoint(Street street, Creature creature) {
		switch (street.getDirection()) {
		case 2:
			return new Point(creature.getCurrentPosition().x, creature.getCurrentPosition().y + 1);

		case 3:
			return new Point(creature.getCurrentPosition().x - 1, creature.getCurrentPosition().y);

		case 4:
			return new Point(creature.getCurrentPosition().x, creature.getCurrentPosition().y - 1);

		default:
			return new Point(creature.getCurrentPosition().x + 1, creature.getCurrentPosition().y);
		}
	}

	public static boolean isTouristOnWay(Hero hero, Car car, Point next) {
		Point a = new Point(hero.getCurrentPosition().x, hero.getCurrentPosition().y);
		Point b = new Point(hero.getCurrentPosition().x + hero.getHorizontalUnits(), hero.getCurrentPosition().y);
		Point c = new Point(hero.getCurrentPosition().x, hero.getCurrentPosition().y + hero.getVerticalUnits());
		Point d = new Point(hero.getCurrentPosition().x + hero.getHorizontalUnits(),
				hero.getCurrentPosition().y + hero.getVerticalUnits());
		Rectangle rect2 = new Rectangle(next.x, next.y, car.getHorizontalUnits(), car.getVerticalUnits());
		return rect2.contains(a) || rect2.contains(b) || rect2.contains(c) || rect2.contains(d);
	}
}
