package io.state.machine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"io.state.machine",
		"io.state.machine.statemachine"
})
public class MachineApplication {

	public static void main(String[] args){
		SpringApplication.run(MachineApplication.class, args);
	}

}
