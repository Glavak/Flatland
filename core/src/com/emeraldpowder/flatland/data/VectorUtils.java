package com.emeraldpowder.flatland.data;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class VectorUtils
{
    /**
     * Given three collinear points p, q, r,  checks if point q lies on line segment pr
     */
    public static boolean onSegment(Vector2 p, Vector2 q, Vector2 r)
    {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    /**
     * @return orientation or given ordered triplet (p, q, r)
     */
    public static TripletOrientation orientation(Vector2 p, Vector2 q, Vector2 r)
    {
        float val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (MathUtils.isZero(val)) return TripletOrientation.Collinear;
        return (val > 0) ? TripletOrientation.Clockwise : TripletOrientation.Counterclockwise;
    }

    public static boolean linesIntersects(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2)
    {
        TripletOrientation o1 = orientation(p1, q1, p2);
        TripletOrientation o2 = orientation(p1, q1, q2);
        TripletOrientation o3 = orientation(p2, q2, p1);
        TripletOrientation o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4) return true;

        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == TripletOrientation.Collinear && onSegment(p1, p2, q1)) return true;

        // p1, q1 and p2 are collinear and q2 lies on segment p1q1
        if (o2 == TripletOrientation.Collinear && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == TripletOrientation.Collinear && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == TripletOrientation.Collinear && onSegment(p2, q1, q2)) return true;

        return false;
    }

    public static Vector2 projectVectors(Vector2 base, Vector2 vectorToProject)
    {
        Vector2 baseNormalized = base.cpy().nor();
        float projectionLength = baseNormalized.dot(vectorToProject);
        return baseNormalized.scl(projectionLength);
    }

    public enum TripletOrientation
    {
        Collinear,
        Clockwise,
        Counterclockwise
    }
}
