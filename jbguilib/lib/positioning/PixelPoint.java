package org.jbguilib.lib.positioning;

public class PixelPoint {
  private short x, y;

  public PixelPoint(int x, int y) {
    this.x = (short)x;
    this.y = (short)y;
  }

  public short getX() { return x; }
  public short getY() { return y; }

  public void setX(short x) { this.x = x; }
  public void setY(short y) { this.y = y; }

  
}
