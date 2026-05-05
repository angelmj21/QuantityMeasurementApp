public class QuantityMeasurementApp {

    interface IMeasurable {
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
    }

    enum WeightUnit implements IMeasurable {
        KG(1.0), GRAM(0.001);
        private final double f;
        WeightUnit(double f) { this.f = f; }
        public double convertToBaseUnit(double v) { return v * f; }
        public double convertFromBaseUnit(double v) { return v / f; }
    }

    static class Quantity<U extends IMeasurable> {
        double value;
        U unit;

        Quantity(double value, U unit) {
            this.value = value;
            this.unit = unit;
        }

        double toBase() {
            return unit.convertToBaseUnit(value);
        }

        Quantity<U> add(Quantity<U> o, U t) {
            return new Quantity<>(t.convertFromBaseUnit(toBase() + o.toBase()), t);
        }

        Quantity<U> subtract(Quantity<U> o, U t) {
            return new Quantity<>(t.convertFromBaseUnit(toBase() - o.toBase()), t);
        }

        double divide(Quantity<U> o) {
            if (o.toBase() == 0) throw new ArithmeticException();
            return toBase() / o.toBase();
        }
    }

    public static void main(String[] args) {
        Quantity<WeightUnit> a = new Quantity<>(10, WeightUnit.KG);
        Quantity<WeightUnit> b = new Quantity<>(5, WeightUnit.KG);

        System.out.println(a.subtract(b, WeightUnit.KG).value);
        System.out.println(a.divide(b));
    }
}