public class QuantityMeasurementApp {

    enum Unit {
        FEET(12.0),
        INCH(1.0);

        double factor;

        Unit(double factor) {
            this.factor = factor;
        }

        double toBase(double v) {
            return v * factor;
        }

        double fromBase(double v) {
            return v / factor;
        }
    }

    static class Quantity {
        double value;
        Unit unit;

        Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        double toBase() {
            return unit.toBase(value);
        }
    }

    public static void main(String[] args) {
        Quantity q = new Quantity(1, Unit.FEET);
        System.out.println("UC8 base: " + q.toBase());
    }
}