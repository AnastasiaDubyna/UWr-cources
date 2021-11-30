package structures;

import java.util.Objects;

public class Pair implements Cloneable, Comparable<Pair>{
    public final String key;
    private int value;

    public Pair(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Pair o) {
        return key.compareTo(o.key);
    }

    @Override
    public Pair clone() {
        return new Pair(key, this.getValue());
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Pair p && key.equals(p.key));
    }

}
