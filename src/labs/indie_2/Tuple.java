package labs.indie_2;

public class Tuple<T, U> {
    private T t;
    private U u;

    public Tuple(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T get0() {
        return t;
    }

    public U get1() {
        return u;
    }

    public void set0(T t) {
        this.t = t;
    }

    public void set1(U u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "(" + t + ", " + u + ")";
    }
}

