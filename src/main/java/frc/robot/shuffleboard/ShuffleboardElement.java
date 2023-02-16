package frc.robot.shuffleboard;

import edu.wpi.first.networktables.GenericEntry;
import lombok.NonNull;

public class ShuffleboardElement<T> {

    @NonNull
    private final String name;
    private final T element;
    private final GenericEntry genericEntry;

    public ShuffleboardElement(T element, String name, GenericEntry genericEntry) {
        this.element = element;
        this.name = name;
        this.genericEntry = genericEntry;
    }

    public ShuffleboardElement(String name) {
        this.name = name;
        this.element = null;
        this.genericEntry = null;
    }

    public String getName() {
        return name;
    }

    public T getElement() {
        return element;
    }

    public GenericEntry getGenericEntry() {
        return genericEntry;
    }
}
