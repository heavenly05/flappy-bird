package org.jbguilib;

import org.jbguilib.lib.scene.Scene;
import org.jbguilib.lib.scene.SceneManager;
import org.jbguilib.lib.window.Window;

public class Main {
	public static void main(String[] args) {
		Window window = new Window.Builder()
      .title("Destroyer Of Worlds!")
      .backgroundColor(74, 73, 92)
      .build();
    
    Scene scene = new Scene("defaultScene") {};
    SceneManager.getInstance().setActiveScene("defaultScene");

    window.run();
  }
}