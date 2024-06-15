package org.jbguilib.lib.scene;

import java.util.*;

public class SceneManager {
  // =================== STATIC ============================
  private static SceneManager instance;

  public static synchronized SceneManager getInstance() {
    if (SceneManager.instance == null) {
      SceneManager.instance = new SceneManager();
    }

    return SceneManager.instance;
  }

  // ===================== INSTANCE =======================
  private Map<String, Scene> sceneMap = new HashMap<>();
  private Scene activeScene;

  public void registerScene(Scene scene) {
    this.sceneMap.put(scene.getName(), scene);
  }

  public void setActiveScene(String sceneName) {
    this.activeScene = sceneMap.get(sceneName);
  }

  public void displaySceneFrame() {
    activeScene.drawFrame();
  }


}
