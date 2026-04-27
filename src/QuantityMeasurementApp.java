public class QuantityMeasurementApp {

    enum WeightUnit {
        KG(1.0),
        GRAM(0.001),
        POUND(0.453592);

        double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        double toBase(double v) {
            return v * factor;
        }

        double fromBase(double v) {
            return v / factor;
        }
    }

    static class QuantityWeight {
        double value;
        WeightUnit unit;

        QuantityWeight(double value, WeightUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        double toBase() {
            return unit.toBase(value);
        }

        boolean equalsWeight(QuantityWeight other) {
            return Math.abs(this.toBase() - other.toBase()) < 1e-6;
        }

        QuantityWeight add(QuantityWeight other, WeightUnit target) {
            double sum = this.toBase() + other.toBase();
            return new QuantityWeight(target.fromBase(sum), target);
        }
    }

    public static void main(String[] args) {
        QuantityWeight w1 = new QuantityWeight(1, WeightUnit.KG);
        QuantityWeight w2 = new QuantityWeight(1000, WeightUnit.GRAM);

        System.out.println("UC9 equal: " + w1.equalsWeight(w2));
        System.out.println("UC9 add: " + w1.add(w2, WeightUnit.KG).value);
    }
}