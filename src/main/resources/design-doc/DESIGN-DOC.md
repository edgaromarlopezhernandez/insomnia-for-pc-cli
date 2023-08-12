# INSOMNIA FOR PC CLI

Author(s): Edgar Omar Lopez H

Status: Ready for review

[Draft, Ready for review, In Review, Reviewed]

Created at: 2022-08-22

Last Updated: 2022-08-23

## Contents
- Objective
- Goals
- Non-Goals
- Detailed Design
  - Solution 1: Spring shell
    - Details
  - Solution 2: Picocli
    - Details
- Conclusion


## Objective
Create a program that moves the mouse when it detects that you are away from your computer.

It does nothing if you are using your computer, making it useful for tricking your machine into thinking you are still using it.

## Goals
- Command to initiate it.
- Argument to set the time in seconds for how long to wait before moving the cursor.
- Argument to set how many pixels the mouse should move. Default 1.

## Non-Goals
- Aditional arguments

## Detailed Design

The design of this solution is entirely focused on the console. The use of commands and arguments is what is aimed for, just like in any other Command Line Interface.

### CLI to avoid computer to sleep
#### Solution 1 (**Preferred**) Spring shell
Spring Shell project can helps us create our CLI quite easy and the fact that this project already comes with built-in commands, such as clear screen, gorgeous help and exit is a big plus.

- [Spring Shell Project Doc](https://docs.spring.io/spring-shell/docs/3.0.7/docs/index.html#what-is-spring-shell)

```Java
@ShellComponent
public class MyCommands {

	@ShellMethod(key = "hello-world")
	public String helloWorld(
		@ShellOption(defaultValue = "spring") String arg
	) {
		return "Hello world " + arg;
	}
}
```

Building a command like this will show it in the CLI

```
My Commands
  hello-world:
```

And you can run it:

```
shell:>hello-world
Hello world spring

shell:>hello-world --arg boot
Hello world boot
```

As shown in the previous snippets, the setup and the assistance provided by the Spring Shell project are enormous, which is why I believe it's the best option to move forward with our project.

#### Solution 2 Picocli

Browsing through the documentation, we find a lot of similarity in how the commands are declared and structured. 

- [Picocli Doc](https://picocli.info/)

```
@Command(name = "add")
public void addCommand() {
    System.out.println("Adding some files to the staging area");
}

@Command(name = "commit")
public void commitCommand() {
    System.out.println("Committing files in the staging area, how wonderful?");
}
```

However, we noticed a significant inconvenience when it comes to running it, as here the application has to be executed with the required arguments manually from console every time a command needs to be entered. In comparison, Spring Shell provides us with a loop where the user can enter as many commands as they want without having to run the application over and over again.

## Conclusion

With this design document, we have realized that Spring Shell aligns more with what is needed for this project. Therefore, we will approach the solution in this manner. The setup, predefined commands, the loop it provides similar to that of a shell, and the ease of declaring commands have all led us to definitively choose Spring Shell.
