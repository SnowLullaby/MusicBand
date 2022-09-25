package commandService;

public class NoCommandError extends Exception {
    @Override
    public String getMessage(){
        return "Такой команды не существует!";
    }

}
