package com.emeraldpowder.flatland.world.shapes;

import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Test;

public class PhysicsShapeCollisionsTest
{
    @Test
    public void lineWithLineDistant()
            throws Exception
    {
        IPhysicsShape shapeA = new Line(new Vector2(-2,0), new Vector2(5,5));
        IPhysicsShape shapeB = new Line(new Vector2(10,11), new Vector2(12,13));

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertFalse(actual);
    }

    @Test
    public void lineWithLineCollinear()
            throws Exception
    {
        IPhysicsShape shapeA = new Line(new Vector2(0,1), new Vector2(10,11));
        IPhysicsShape shapeB = new Line(new Vector2(1,0), new Vector2(11,10));

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertFalse(actual);
    }

    @Test
    public void lineWithLineContinuing()
            throws Exception
    {
        IPhysicsShape shapeA = new Line(new Vector2(0,0), new Vector2(7,7));
        IPhysicsShape shapeB = new Line(new Vector2(2,2), new Vector2(10,10));

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertTrue(actual);
    }

    @Test
    public void lineWithLineColliding()
            throws Exception
    {
        IPhysicsShape shapeA = new Line(new Vector2(0,0), new Vector2(7,7));
        IPhysicsShape shapeB = new Line(new Vector2(7,0), new Vector2(0,7));

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertTrue(actual);
    }

    @Test
    public void lineWithLineTouching()
            throws Exception
    {
        IPhysicsShape shapeA = new Line(new Vector2(0,0), new Vector2(2,2));
        IPhysicsShape shapeB = new Line(new Vector2(2,2), new Vector2(7,0));

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertFalse(actual);
    }

    @Test
    public void lineWithLineTouchingCenter()
            throws Exception
    {
        IPhysicsShape shapeA = new Line(new Vector2(0,0), new Vector2(2,0));
        IPhysicsShape shapeB = new Line(new Vector2(2,-1), new Vector2(2,1));

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertFalse(actual);
    }

    @Test
    public void sphereWithSphereDistant()
            throws Exception
    {
        IPhysicsShape shapeA = new Sphere(new Vector2(0,0), 2);
        IPhysicsShape shapeB = new Sphere(new Vector2(7,0), 2);

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertFalse(actual);
    }

    @Test
    public void sphereWithSphereTouches()
            throws Exception
    {
        IPhysicsShape shapeA = new Sphere(new Vector2(0,0), 2);
        IPhysicsShape shapeB = new Sphere(new Vector2(0,5), 3);

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertFalse(actual);
    }

    @Test
    public void sphereWithSphereInside()
            throws Exception
    {
        IPhysicsShape shapeA = new Sphere(new Vector2(5,5), 5);
        IPhysicsShape shapeB = new Sphere(new Vector2(2,4), 1);

        boolean actual = shapeA.isCollides(shapeB);

        Assert.assertTrue(actual);
    }
}