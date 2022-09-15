package commands;

public class HelpCommand implements Command {
    public void execute(){
        for (var co: CommandList.getCommandsOutput()) {
            System.out.printf("%s: %s\n", co.name, co.desc);
        }
    }

    public String getDesc() {
        return "выводит справку по коммандам";
    }
}
