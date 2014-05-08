package com.mygdx.game;

public class WorldController {
    public Player player;

    public WorldController() {
        Init();
    }

    public void Init() {
        player = new Player();
    }

    public void Update() {
        //player updates
        player.UpdateOrientation();
    }
}
