package com.emeraldpowder.flatland.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

class GameInputListener extends InputAdapter
{
    private int lastX;
    private int deltaX;

    private boolean isForwardPressed;
    private boolean isBackwardPressed;
    private boolean isRightPressed;
    private boolean isLeftPressed;

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        deltaX += screenX - lastX;
        lastX = screenX;
        return true;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        setKey(keycode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        setKey(keycode, false);
        return true;
    }

    private void setKey(int keycode, boolean isPressed)
    {
        switch (keycode)
        {
            case Input.Keys.W:
                isForwardPressed = isPressed;
                break;
            case Input.Keys.A:
                isLeftPressed = isPressed;
                break;
            case Input.Keys.S:
                isBackwardPressed = isPressed;
                break;
            case Input.Keys.D:
                isRightPressed = isPressed;
                break;
        }
    }

    /**
     * @return Vector2, representing direction user desire to move. Y axis is forward, X axis is right
     */
    public Vector2 getMovingDirection()
    {
        Vector2 result = new Vector2();

        if (isForwardPressed) result.add(0, 1);
        if (isLeftPressed) result.add(-1, 0);
        if (isBackwardPressed) result.add(0, -1);
        if (isRightPressed) result.add(1, 0);

        return result;
    }

    /**
     * @return movement of mouse by X axis in pixels since last call
     */
    public int getDeltaX()
    {
        int result = deltaX;
        deltaX = 0;
        return result;
    }
}
