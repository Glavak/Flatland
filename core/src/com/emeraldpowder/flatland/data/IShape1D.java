package com.emeraldpowder.flatland.data;

public interface IShape1D
{
    /**
     * @return object starting edge coordinate 0 to 1
     */
    float getXStart();

    /**
     * @return object ending edge coordinate 0 to 1
     */
    float getXEnd();

    float getLength();

    /**
     * @param farCullingLine distance, after which depth is 0
     * @return distance from object to viewer
     */
    float[] getDepth(int screenLength, float farCullingLine);
}
