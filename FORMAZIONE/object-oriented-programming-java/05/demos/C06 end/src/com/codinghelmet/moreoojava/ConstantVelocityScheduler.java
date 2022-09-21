package com.codinghelmet.moreoojava;

import java.util.List;

public class ConstantVelocityScheduler implements PaintingScheduler {
    @Override
    public WorkStream schedule(List<Painter> painters, double sqMeters) {
        return this.schedule(painters, sqMeters, this.estimateTotalVelocity(painters, sqMeters));
    }

    private WorkStream schedule(List<Painter> painters, double sqMeters, Velocity totalVelocity) {
        return WorkAssignment.stream(Painter.stream(painters)
            .map(painter -> painter.assign(sqMeters * painter.estimateVelocity(sqMeters).divideBy(totalVelocity))));
    }

    private Velocity estimateTotalVelocity(List<Painter> painters, double sqMeters) {
        return Painter.stream(painters)
            .map(painter -> painter.estimateVelocity(sqMeters))
            .reduce(Velocity::add)
            .orElse(Velocity.ZERO);
    }
}
