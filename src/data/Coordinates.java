package data;

/**
 * X-Y coordinates.
 */

public class Coordinates {
    private Float x;

    private Long y;

    public Coordinates(Float x, Long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X-coordinate.
     */

    public Float getX(){return x;}

    /**
     * @return Y-coordinate.
     */

    public Long getY(){return y;}

    @Override
    public String toString(){return "X:" + x + "Y:" +y;}

    @Override
    public int hashCode() {
        return y.hashCode() + x.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && y.equals(coordinatesObj.getY());
        }
        return false;
    }



}
