package com.spring.learn_spring;

import com.spring.learn_spring.enterprise.example.web.MyWebController;
import org.springframework.boot.SpringApplication;
import com.spring.learn_spring.game.GameRunner;
import com.spring.learn_spring.game.PacmanGame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LearnSpringApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(LearnSpringApplication.class, args);
//		PacmanGame game = new PacmanGame();
//		GameRunner runner = new GameRunner(game);
		GameRunner runner = context.getBean(GameRunner.class);
		MyWebController controller = context.getBean(MyWebController.class);
		runner.run();
		System.out.println(controller.returnValueFromBusinessService());

	}

}
