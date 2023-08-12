package cli.insomnia.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class InsomniaService {

    public void insomnia() throws InterruptedException, IOException {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime stopFlag = currentTime.plusSeconds(14);
        System.out.println("Let's try to avoid this PC going to sleep...");
        do {
            String so = System.getProperty("os.name");
            System.out.println("Current SO: " + so);
            if(so.contains("Linux")) {
                System.out.println("Linux distro");

                String scriptPath = System.getProperty("user.dir")
                        + System.getProperty("file.separator") + "src"
                        + System.getProperty("file.separator") + "main"
                        + System.getProperty("file.separator") + "resources"
                        + System.getProperty("file.separator") + "scripts"
                        + System.getProperty("file.separator") + "script.sh";

                Process process = Runtime.getRuntime().exec("chmod u+x " + scriptPath);
                process.waitFor();

                int exitValue = process.exitValue();
                if (exitValue == 0) {
                    System.out.println("Script executed successfully");
                    System.out.println("System won't sleep");
                } else {
                    System.out.println("Script failed with exit value: " + exitValue);
                    System.out.println("System failed to avoid this PC going to sleep");
                }
            }
            if(so.contains("Windows")) {
                try {
                    System.out.println("Activating this on windows...");
                    String [] cmd = {"powercfg.exe","/hibernate off"};
                    Runtime.getRuntime().exec(cmd);
                    System.out.println("Prevented to hibernate");
                    String [] cmd1 = {"powercfg.exe","-change","-monitor-timeout-ac", "0"};
                    Runtime.getRuntime().exec(cmd1);
                    System.out.println("Prevented to turn off monitor if idle");
                    String [] cmd2 = {"powercfg.exe","/change","standby-timeout-ac", "0"};
                    Runtime.getRuntime().exec(cmd2);
                    System.out.println("Prevented stand by timeout");
                    System.out.println("Done!");
                } catch (IOException ioe) {
                    System.out.println("WTF?... Something went wrong, sorry!");
                    System.out.println (ioe);
                }
            }
            Thread.sleep(14000L);
        }while (!LocalDateTime.now().isAfter(stopFlag));
    }
}
