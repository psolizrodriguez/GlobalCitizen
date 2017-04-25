
package com.globalcitizen.model.viewpercy;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

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

	public static boolean isCarOnWay(List<Street> lisStreet, Point next, Car currentCar) {
		if (lisStreet != null && lisStreet.size() > 0) {
			for (Street street : lisStreet) {
				if (street.getListCars() != null && street.getListCars().size() > 0) {
					for (Car car : street.getListCars()) {
						if (!car.equals(currentCar)) {
							if (isCarOnWay(currentCar, car, next)) {
								return true;
							}
						}

					}
				}
			}
		}
		return false;
	}

	public static boolean isCarOnWay(Car curentCar, Car car, Point next) {
		int spaceBetweenCars = 10;
		int futureX_1, futureX_2, futureY_1, futureY_2;

		switch (curentCar.getCurrentStreet().getDirection()) {
		case 2:
			futureX_1 = next.x;
			futureY_1 = next.y + curentCar.getVerticalUnits() + spaceBetweenCars;
			futureX_2 = next.x + curentCar.getHorizontalUnits();
			futureY_2 = next.y + curentCar.getVerticalUnits() + spaceBetweenCars;
			break;
		case 3:
			futureX_1 = next.x - spaceBetweenCars;
			futureY_1 = next.y;
			futureX_2 = next.x - spaceBetweenCars;
			futureY_2 = next.y + curentCar.getVerticalUnits();
			break;
		case 4:
			futureX_1 = next.x;
			futureY_1 = next.y - spaceBetweenCars;
			futureX_2 = next.x + curentCar.getHorizontalUnits();
			futureY_2 = next.y - spaceBetweenCars;
			break;
		default:
			futureX_1 = next.x + curentCar.getHorizontalUnits() + spaceBetweenCars;
			futureY_1 = next.y;
			futureX_2 = next.x + curentCar.getHorizontalUnits() + spaceBetweenCars;
			futureY_2 = next.y + curentCar.getVerticalUnits();
			break;
		}

		Point a = new Point(futureX_1, futureY_1);
		Point b = new Point(futureX_2, futureY_2);
		Rectangle rect2 = new Rectangle(car.getCurrentPosition().x, car.getCurrentPosition().y,
				car.getHorizontalUnits(), car.getVerticalUnits());
		return rect2.contains(a) || rect2.contains(b);
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
