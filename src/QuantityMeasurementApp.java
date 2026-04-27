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

        Quantity add(Quantity other) {
            double sum = this.toBase() + other.toBase();
            return new Quantity(unit.fromBase(sum), this.unit);
        }
    }

    public static void main(String[] args) {
        Quantity q1 = new Quantity(1, Unit.FEET);
        Quantity q2 = new Quantity(12, Unit.INCH);

        System.out.println("UC6 sum: " + q1.add(q2).value);
    }
}