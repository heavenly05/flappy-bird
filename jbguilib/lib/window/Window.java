package org.jbguilib.lib.window;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;

import java.nio.IntBuffer;

import org.jbguilib.lib.color.ColorRGBA;
import org.jbguilib.lib.listener.KeyListener;
import org.jbguilib.lib.listener.MouseListener;
import org.jbguilib.lib.positioning.PixelDimension;
import org.jbguilib.lib.scene.SceneManager;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
  public static class Builder {
    private boolean centered = true;
    private boolean visible = true;
    private boolean resizable = true;

    private boolean maximized = false;
    private boolean borderless = false;

    private ColorRGBA backgroundColor = new ColorRGBA(255, 255, 255);
    private PixelDimension windowDimensions = new PixelDimension(800, 600);
  
    private String title = "New Window";

    public Builder centered(boolean centered) { 
      this.centered = centered;
      return this;
    }
    public Builder resizable(boolean resizable) { 
      this.resizable = resizable;
      return this;
    }
    public Builder visible(boolean visible) { 
      this.visible = visible;
      return this;
    }
    public Builder maximized(boolean maximized) { 
      this.maximized = maximized;
      return this;
    }
    public Builder title(String title) { 
      this.title = title;
      return this;
    }
    public Builder borderless(boolean borderless) { 
      this.borderless = borderless;
      return this;
    }
  
    public Builder backgroundColor(int red, int green, int blue) { 
      this.backgroundColor.setRed(red);
      this.backgroundColor.setGreen(green);
      this.backgroundColor.setBlue(blue);
      return this;
    }
  
    public Builder backgroundColor(int red, int green, int blue, int alpha) { 
      this.backgroundColor.setRed(red);
      this.backgroundColor.setGreen(green);
      this.backgroundColor.setBlue(blue);
      this.backgroundColor.setAlpha(alpha);
      return this;
    }
  
    public Builder windowDimensions(int width, int height) { 
      this.windowDimensions.setWidth(width);
      this.windowDimensions.setWidth(width);
      return this;
    }

    public Window build() {
      return new Window(
        centered, resizable, 
        visible, maximized,
        backgroundColor, windowDimensions, 
        title, borderless
      );
    }
  }

  private boolean centered, resizable, visible, maximized, borderless;
  private ColorRGBA backgroundColor;
  private static PixelDimension windowDimensions;
  private String title;
  private long window;

  public Window(boolean centered, boolean resizable, boolean visible, boolean maximized,
      ColorRGBA backgroundColor, PixelDimension windowDimensions, String title, boolean borderless) {
    this.centered = centered;
    this.resizable = resizable;
    this.visible = visible;
    this.maximized = maximized;
    this.backgroundColor = backgroundColor;
    Window.windowDimensions = windowDimensions;
    this.title = title;
    this.borderless = borderless;
  }

  private void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if ( !GLFW.glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, (this.visible ?  1 : 0)); 
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, (this.resizable ? 1 : 0)); 
    GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, (!this.borderless ? 1 : 0)); 
    GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, (this.maximized ? 1 : 0));

		window = GLFW.glfwCreateWindow(Window.windowDimensions.getWidth(), Window.windowDimensions.getHeight(), this.title, MemoryUtil.NULL, MemoryUtil.NULL);
		if ( window == MemoryUtil.NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		try ( MemoryStack stack = MemoryStack.stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			GLFW.glfwGetWindowSize(window, pWidth, pHeight);
			GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

      if(centered) {
        GLFW.glfwSetWindowPos(window,
          (vidmode.width() - pWidth.get(0)) / 2,
          (vidmode.height() - pHeight.get(0)) / 2
        );
      }
		} 

    glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
    glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
    glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
    glfwSetKeyCallback(window, KeyListener::KeyCallback);

		GLFW.glfwMakeContextCurrent(window);
    GLFW.glfwSwapInterval(1); 
    if(this.visible) { GLFW.glfwShowWindow(window); }
  }

  private void loop() {
		GL.createCapabilities();

		GL11.glClearColor(this.backgroundColor.getRed1f(), this.backgroundColor.getGreen1f(), this.backgroundColor.getBlue1f(), this.backgroundColor.getAlpha());

		while (!GLFW.glfwWindowShouldClose(window)) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 

      SceneManager.getInstance().displaySceneFrame();

			GLFW.glfwSwapBuffers(window); 
			GLFW.glfwPollEvents();
		}
	}

	public void run() {
		init();
		loop();

		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}
}
