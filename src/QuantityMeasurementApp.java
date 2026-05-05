public class QuantityMeasurementApp {

    // Step 1: Common Interface
    interface IMeasurable {
        double getConversionFactor();
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }

    // Step 2: Refactored WeightUnit
    enum WeightUnit implements IMeasurable {
        KG(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) {
            this.factor = factor;
        }

        public double getConversionFactor() {
            return factor;
        }

        public double convertToBaseUnit(double value) {
            return value * factor;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue / factor;
        }

        public String getUnitName() {
            return name();
        }
    }

    // Step 4: Generic Quantity Class
    static class Quantity<U extends IMeasurable> {
        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Invalid quantity");
            }
            this.value = value;
            this.unit = unit;
        }

        public double toBase() {
            return unit.convertToBaseUnit(value);
        }

        public Quantity<U> convertTo(U targetUnit) {
            double base = this.toBase();
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity<>(round(converted), targetUnit);
        }

        public Quantity<U> add(Quantity<U> other) {
            return add(other, this.unit);
        }

        public Quantity<U> add(Quantity<U> other, U targetUnit) {
            double sum = this.toBase() + other.toBase();
            return new Quantity<>(round(targetUnit.convertFromBaseUnit(sum)), targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Quantity<?> that)) return false;

            if (this.unit.getClass() != that.unit.getClass()) return false;

            return Double.compare(this.toBase(), that.toBase()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBase());
        }

        @Override
        public String toString() {
            return value + " " + unit.getUnitName();
        }

        private double round(double value) {
            return Math.round(value * 100.0) / 100.0;
        }
    }

    // Step 5: Simplified App
    public static void main(String[] args) {

        Quantity<WeightUnit> w1 = new Quantity<>(1, WeightUnit.KG);
        Quantity<WeightUnit> w2 = new Quantity<>(1000, WeightUnit.GRAM);

        System.out.println("UC10 equal: " + w1.equals(w2));
        System.out.println("UC10 add: " + w1.add(w2, WeightUnit.KG));
    }
}