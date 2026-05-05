public class QuantityMeasurementApp {

    interface IMeasurable {
        double getConversionFactor();
        double convertToBaseUnit(double value);
        double convertFromBaseUnit(double baseValue);
        String getUnitName();
    }

    enum WeightUnit implements IMeasurable {
        KG(1.0), GRAM(0.001), POUND(0.453592);
        private final double factor;
        WeightUnit(double factor) { this.factor = factor; }
        public double getConversionFactor() { return factor; }
        public double convertToBaseUnit(double v) { return v * factor; }
        public double convertFromBaseUnit(double v) { return v / factor; }
        public String getUnitName() { return name(); }
    }

    enum VolumeUnit implements IMeasurable {
        LITRE(1.0), MILLILITRE(0.001), GALLON(3.78541);
        private final double factor;
        VolumeUnit(double factor) { this.factor = factor; }
        public double getConversionFactor() { return factor; }
        public double convertToBaseUnit(double v) { return v * factor; }
        public double convertFromBaseUnit(double v) { return v / factor; }
        public String getUnitName() { return name(); }
    }

    static class Quantity<U extends IMeasurable> {
        private final double value;
        private final U unit;

        public Quantity(double value, U unit) {
            this.value = value;
            this.unit = unit;
        }

        public double toBase() {
            return unit.convertToBaseUnit(value);
        }

        public Quantity<U> convertTo(U target) {
            return new Quantity<>(target.convertFromBaseUnit(toBase()), target);
        }

        public Quantity<U> add(Quantity<U> other, U target) {
            double sum = this.toBase() + other.toBase();
            return new Quantity<>(target.convertFromBaseUnit(sum), target);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Quantity<?> q)) return false;
            if (this.unit.getClass() != q.unit.getClass()) return false;
            return Math.abs(this.toBase() - q.toBase()) < 1e-6;
        }
    }

    public static void main(String[] args) {
        Quantity<VolumeUnit> v1 = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000, VolumeUnit.MILLILITRE);

        System.out.println(v1.equals(v2));
        System.out.println(v1.convertTo(VolumeUnit.MILLILITRE).value);
    }
}