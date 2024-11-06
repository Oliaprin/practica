package task2;

public class Interval {
    private double start;
    private double end;
    private boolean startInclusive;
    private boolean endInclusive;

    public Interval(double start, double end, boolean startInclusive, boolean endInclusive) {
        if (start > end || (start == end && (!startInclusive || !endInclusive))) {
            throw new IllegalArgumentException("Некорректный интервал");
        }
        this.start = start;
        this.end = end;
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    public boolean intersects(Interval other) {
        return this.start < other.end && this.end > other.start
                || (this.start == other.end && (this.startInclusive || other.endInclusive))
                || (this.end == other.start && (this.endInclusive || other.startInclusive));
    }

    public Interval union(Interval other) {
        if (!this.intersects(other)) {
            throw new IllegalArgumentException("Интервалы не пересекаются");
        }
        double newStart = Math.min(this.start, other.start);
        double newEnd = Math.max(this.end, other.end);
        boolean newStartInclusive = this.start < other.start || (this.start == other.start && (this.startInclusive || other.startInclusive));
        boolean newEndInclusive = this.end > other.end || (this.end == other.end && (this.endInclusive || other.endInclusive));
        return new Interval(newStart, newEnd, newStartInclusive, newEndInclusive);
    }

    public Interval intersection(Interval other) {
        if (!this.intersects(other)) {
            throw new IllegalArgumentException("Интервалы не пересекаются");
        }
        double newStart = Math.max(this.start, other.start);
        double newEnd = Math.min(this.end, other.end);
        boolean newStartInclusive = this.start > other.start || (this.start == other.start && (this.startInclusive || other.startInclusive));
        boolean newEndInclusive = this.end < other.end || (this.end == other.end && (this.endInclusive || other.endInclusive));
        return new Interval(newStart, newEnd, newStartInclusive, newEndInclusive);
    }

    public Interval add(Interval other) {
        return new Interval(this.start + other.start, this.end + other.end,
                this.startInclusive && other.startInclusive,
                this.endInclusive && other.endInclusive);
    }

    public Interval subtract(Interval other) {
        return new Interval(this.start - other.end, this.end - other.start,
                this.startInclusive && !other.endInclusive,
                this.endInclusive && !other.startInclusive);
    }

    public Interval multiply(double factor) {
        if (factor == 0) return new Interval(0, 0, true, true);
        double newStart = factor < 0 ? factor * this.end : factor * this.start;
        double newEnd = factor < 0 ? factor * this.start : factor * this.end;
        return new Interval(newStart, newEnd, factor > 0 ? this.startInclusive : this.endInclusive,
                factor > 0 ? this.endInclusive : this.startInclusive);
    }

    public Interval divide(double divisor) {
        if (divisor == 0) throw new ArithmeticException("Нельзя делить на ноль");
        double newStart = (this.start / divisor);
        double newEnd = (this.end / divisor);
        return new Interval(newStart, newEnd, this.startInclusive, this.endInclusive);
    }

    public static double distanceBetween(Interval[] intervals) {
        if (intervals.length == 0) throw new IllegalArgumentException("Не указаны интервалы");
        double min = intervals[0].start;
        double max = intervals[0].end;
        for (Interval interval : intervals) {
            min = Math.min(min, interval.start);
            max = Math.max(max, interval.end);
        }
        return max - min;
    }

    // Геттеры для полей
    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public boolean isStartInclusive() {
        return startInclusive;
    }

    public boolean isEndInclusive() {
        return endInclusive;
    }

    @Override
    public String toString() {
        return (startInclusive ? "[" : "(") + start + ", " + end + (endInclusive ? "]" : ")");
    }
}
