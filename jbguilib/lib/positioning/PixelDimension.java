package org.jbguilib.lib.positioning;

public class PixelDimension {
  private short width, height;

  public PixelDimension(int width, int height) {
    this.width = (short)width;
    this.height = (short)height;
  }

  public short getWidth() { return width; }
  public short getHeight() { return height; }

  public void setWidth(int width) { this.width = (short)width; }
  public void setHeight(int height) { this.height = (short)height; }

  
}
