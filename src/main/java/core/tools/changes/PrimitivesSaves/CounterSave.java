package core.tools.changes.PrimitivesSaves;

import primitive.calculation.Counter;

import java.awt.*;

public class CounterSave implements Saves<Counter> {
    private final double size;
    private final Color color;

    public CounterSave(Counter counter) {
        this(counter.getSize(), counter.getColor());
    }

    public CounterSave(double size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public void applySave(Counter counter){
        counter.setColor(color);
        counter.setSize(size);

    }
}
