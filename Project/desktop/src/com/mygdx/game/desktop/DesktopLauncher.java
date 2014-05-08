package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.KernelDefender;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Kernel Defender";
        config.width = 1280;
        config.height = 720;
        config.addIcon("icons/windowicon.png", Files.FileType.Internal);
		new LwjglApplication(new KernelDefender(), config);
	}
}
