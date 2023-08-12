package cli.insomnia.commands;

import cli.insomnia.service.InsomniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
public class InsomniaCommands {
    @Autowired
    private InsomniaService service;
    @ShellMethod(key = "insomnia", value = "Start insomnia for PC, it will prevent this computer to going to sleep")
    public String insomnia() throws InterruptedException, IOException {
        System.out.println("INSOMNIA FOR PC!! \n App that prevents your PC from going to sleep, making it useful for tricking your machine into thinking you are still using it. \n...\n..\n.");
        service.insomnia();
        return "Bye bye :)!";
    }
}
