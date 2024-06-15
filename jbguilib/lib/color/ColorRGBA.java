package org.jbguilib.lib.color;

public class ColorRGBA {
  private short red, blue, green;
  private float alpha = 1.0f;

  public ColorRGBA(int red, int blue, int green) {
    this.red = (short)red;
    this.blue = (short)blue;
    this.green = (short)green;
  }
  
  public ColorRGBA(int red, int blue, int green, float alpha) {
    this.red = (short)red;
    this.blue = (short)blue;
    this.green = (short)green;
    this.alpha = (float)alpha;
  }

  public short getRed255() { return red; }
  public short getBlue255() { return blue; }
  public short getGreen255() { return green; }
  public float getAlpha() { return alpha; }

  public float getRed1f() { return red / 255.0f; }
  public float getBlue1f() { return blue / 255.0f; }
  public float getGreen1f() { return green / 255.0f; }

  public void setRed(int red) { this.red = (short)red; }
  public void setGreen(int green) { this.green = (short)green; }
  public void setBlue(int blue) { this.blue = (short)blue; }
  public void setAlpha(float alpha) { this.alpha = alpha; }

  
}
