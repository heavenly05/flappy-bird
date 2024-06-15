package org.jbguilib.lib.component;

public abstract class DynamicComponent extends StaticComponent {
  private float GRAVITY = 10;
  private float mass = 1;

  private void applyPhysics() {}

  public void run() {
    applyPhysics();
    draw();
  }
}
