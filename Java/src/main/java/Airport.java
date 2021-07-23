import Planes.ExperimentalPlane;
import models.MilitaryType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;

/**
 * version: 1.1
 * made by Vitali Shulha
 * 4-Jan-2019
 */

public class Airport {
    private final List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }


    private <T> List<T> getTypeOfPlanes(List<Plane> planes, Class<T> clazz) {
        List<T> classTypes = new ArrayList<>();
        for (Plane plane : planes) {
            if (clazz.isInstance(plane)) {
                classTypes.add(clazz.cast(plane));
            }
        }
        return classTypes;
    }

    public List<PassengerPlane> getPassengersPlanes() {
        return getTypeOfPlanes((List<Plane>) planes, PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getTypeOfPlanes((List<Plane>) planes, MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getTypeOfPlanes((List<Plane>) planes, ExperimentalPlane.class);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengersPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (int i = 0; i < passengerPlanes.size(); i++) {
            if (passengerPlanes.get(i).getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlanes.get(i);
            }
        }

        return planeWithMaxCapacity;
    }

    private void getTypeOfMilitaryPlanes(List<MilitaryPlane> typeOfMilitaryPlanes, List<MilitaryPlane> militaryPlanes, MilitaryType militaryType) {
        for (int i = 0; i < militaryPlanes.size(); i++) {
            MilitaryPlane plane = militaryPlanes.get(i);
            if (plane.getType() == militaryType) {
                typeOfMilitaryPlanes.add(plane);
            }
        }
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        getTypeOfMilitaryPlanes(transportMilitaryPlanes, militaryPlanes, MilitaryType.TRANSPORT);
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        getTypeOfMilitaryPlanes(bomberMilitaryPlanes, militaryPlanes, MilitaryType.BOMBER);
        return bomberMilitaryPlanes;
    }

    public Airport sortByMaxDistance() {
        planes.sort((Comparator<Plane>) (o1, o2) -> o1.getMaxFlightDistance() - o2.getMaxFlightDistance());
        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort((Comparator<Plane>) (o1, o2) -> o1.getMaxSpeed() - o2.getMaxSpeed());
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort((Comparator<Plane>) (o1, o2) -> o1.getMaxLoadCapacity() - o2.getMaxLoadCapacity());
        return this;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    private void print(Collection<? extends Plane> collection) {
        Iterator<? extends Plane> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

}
