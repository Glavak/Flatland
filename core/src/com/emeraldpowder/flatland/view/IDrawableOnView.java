package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.math.Vector2;
import com.emeraldpowder.flatland.data.Angle;

public interface IDrawableOnView
{
    void draw(WorldFrame worldFrame, Vector2 viewerPosition, Angle viewingAngle);
}
