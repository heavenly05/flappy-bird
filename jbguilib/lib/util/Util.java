package org.jbguilib.lib.util;

import org.jbguilib.lib.positioning.PercentDimension;
import org.jbguilib.lib.positioning.PercentPoint;
import org.jbguilib.lib.positioning.PixelDimension;
import org.jbguilib.lib.positioning.PixelPoint;

public class Util {

  public static int booleanToInt(boolean bool) {
    return !bool ? 1 : 0;
  }

  public static PercentPoint pixelToPercentPoint(PixelDimension window, PixelPoint pixel) {
    return new PercentPoint((float)window.getWidth() / pixel.getX(), (float)window.getHeight() / pixel.getY());
  }

  public static PercentDimension pixelToPercentDimension(PixelDimension window, PixelDimension dim) {
    return new PercentDimension(
      (float)window.getWidth() / dim.getWidth(),
      (float)window.getHeight() / dim.getHeight()
    );
  }
}
