package dev.breno;

import dev.breno.engine.*;
import dev.breno.enviroment.Ball;
import dev.breno.enviroment.Line;
import dev.breno.enviroment.Score;
import dev.breno.player.Player;
import dev.breno.player.PlayerInputHandler;

import java.util.List;

public class Game {
    public static void main(String[] args) {
        PlayerInputHandler inputHandlerPlayerOne = new PlayerInputHandler(KeyEventEnum.VK_W, KeyEventEnum.VK_S);
        PlayerInputHandler inputHandlerPlayerTwo = new PlayerInputHandler(KeyEventEnum.VK_UP, KeyEventEnum.VK_DOWN);

        Player playerOne = new Player(Configuration.getOffside(), (Configuration.getStaticHeight() / 2) - 20, 5, 60, inputHandlerPlayerOne);
        Player playerTwo = new Player(Configuration.getStaticWidthWithOffside()-10, (Configuration.getStaticHeight() / 2) - 20, 5, 60, inputHandlerPlayerTwo);

        Score scoreOne = new Score((Configuration.getStaticWidth() / 2) - 30, Configuration.getOffside() + 24, 0, 0);
        Score scoreTwo = new Score((Configuration.getStaticWidth() / 2) + 30, Configuration.getOffside() + 24, 0, 0);

        Line line = new Line((Configuration.getStaticWidth() / 2) + 5, 0, 5, 20);

        Ball ball = new Ball(Configuration.getStaticWidth() / 2, Configuration.getStaticHeight() / 2, 15, 15)
                .setPlayerOne(playerOne)
                .setScoreOne(scoreOne)
                .setPlayerTwo(playerTwo)
                .setScoreTwo(scoreTwo);

        List<Element> observersRender = List.of(playerOne, playerTwo, ball, scoreOne, scoreTwo, line);
        List<Element> observersGameLoop = List.of(playerOne, playerTwo, ball);

        RenderConfig renderConfig = new RenderConfig("Rogue Pong", Configuration.getStaticWidth(), Configuration.getStaticHeight());

        GameInputHandler gameInputHandler = new GameInputHandler();

        DoubleBufferRender bufferRender = new DoubleBufferRender(renderConfig)
                .addKeyListeners(inputHandlerPlayerOne, inputHandlerPlayerTwo, gameInputHandler)
                .setObservers(observersRender);

        GameLoop gameLoop = GameLoop.getInstance()
                .setBufferRender(bufferRender)
                .setFramesPerSeconds(Configuration.getFPS())
                .setObservers(observersGameLoop);

        gameLoop.start();
    }
}
