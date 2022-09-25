package commandService;

public class NoParamsError extends Exception {
    @Override
    public String getMessage(){
        return "Недостаточно аргументов!";
    }
}
