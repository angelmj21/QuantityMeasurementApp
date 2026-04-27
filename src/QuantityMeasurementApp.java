public class QuantityMeasurementApp {

    enum Unit {
        FEET(12.0),
        INCH(1.0);

        private final double factor;

        Unit(double factor) {
            this.factor = factor;
        }

        double toBase(double value) {
            return value * factor;
        }

        double fromBase(double base) {
            return base / factor;
        }
    }

    static class Quantity {
        double value;
        Unit unit;

        Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        double convertTo(Unit target) {
            double base = unit.toBase(value);
            return target.fromBase(base);
        }
    }

    public static void main(String[] args) {

        Quantity q = new Quantity(1.0, Unit.FEET);

        System.out.println("UC5 Conversion: " +
                q.convertTo(Unit.INCH));
    }
}