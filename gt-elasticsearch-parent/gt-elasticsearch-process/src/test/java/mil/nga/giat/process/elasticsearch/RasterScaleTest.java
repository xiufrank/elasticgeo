/**
 * This file is hereby placed into the Public Domain. This means anyone is
 * free to do whatever they wish with this file.
 */
package mil.nga.giat.process.elasticsearch;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RasterScaleTest {

    @Test
    public void testRasterScale_noScale() throws Exception {
        RasterScale scale = new RasterScale(null);
        assertFalse(scale.isScaleSet());
    }
    
    @Test
    public void testRasterScale_emptyScale() throws Exception {
        List<Float> list = new ArrayList<Float>();
        RasterScale scale = new RasterScale(list);
        assertFalse(scale.isScaleSet());
    }
    
    @Test
    public void testRasterScale_maxProvided() throws Exception {
        float scaleMax = 10.0f;
        List<Float> list = new ArrayList<Float>();
        list.add(scaleMax);
        RasterScale scale = new RasterScale(list);
        assertTrue(scale.isScaleSet());
        assertEquals(0, scale.getScaleMin(), 0.0);
        assertEquals(scaleMax, scale.getScaleMax(), 0.0);
    }
    
    @Test
    public void testRasterScale_minMaxProvided() throws Exception {
        float scaleMax = 10.0f;
        float scaleMin = 1.0f;
        List<Float> list = new ArrayList<Float>();
        list.add(scaleMin);
        list.add(scaleMax);
        RasterScale scale = new RasterScale(list);
        assertTrue(scale.isScaleSet());
        assertEquals(scaleMin, scale.getScaleMin(), 0.0);
        assertEquals(scaleMax, scale.getScaleMax(), 0.0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testRasterScale_minMaxSame() throws Exception {
        float scaleMax = 10.0f;
        float scaleMin = scaleMax;
        List<Float> list = new ArrayList<Float>();
        list.add(scaleMin);
        list.add(scaleMax);
        new RasterScale(list);
    }
    
    @Test
    public void testRasterScale_scaleValue() throws Exception {
        float scaleMax = 10.0f;
        float scaleMin = 0.0f;
        List<Float> list = new ArrayList<Float>();
        list.add(scaleMin);
        list.add(scaleMax);
        RasterScale scale = new RasterScale(list);
        scale.prepareScale(30);
        scale.prepareScale(20);
        scale.prepareScale(10);
        assertEquals(10, scale.scaleValue(30), 0.0);
        assertEquals(5, scale.scaleValue(20), 0.0);
        assertEquals(0, scale.scaleValue(10), 0.0);
    }
}