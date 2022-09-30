package commands;

import java.util.Vector;

public class CommandsDesc {
    public static final FullCommandDesc HELP = new FullCommandDesc("help", "выводит справку по коммандам");
    public static final FullCommandDesc INFO = new FullCommandDesc("info", "Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)");
    public static final FullCommandDesc SHOW = new FullCommandDesc("show", "Выводит в стандартный поток вывода все элементы коллекции в строковом предствалении");
    public static final FullCommandDesc ADD = new FullCommandDesc("add","Добавляет новый элемент в коллекцию");
    public static final FullCommandDesc UPDATE = new FullCommandDesc("update","Обновляет значение элемента коллекции, id которого равен заданному");
    public static final FullCommandDesc REMOVE_BY_ID = new FullCommandDesc("remove_by_id","Удаляет элемент из коллекции по id");
    public static final FullCommandDesc CLEAR = new FullCommandDesc("clear","Очищает коллеккцию");
    public static final FullCommandDesc SAVE = new FullCommandDesc("save","Сохраняет колллекцию в файл");
    public static final FullCommandDesc EXECUTE_SCRIPT = new FullCommandDesc("execute_script","Считывает и исполняет скрипт из указанного файла. В скрипте содержатся комманды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
    public static final FullCommandDesc EXIT = new FullCommandDesc("exit","завершает выполнеие программы (без сохранения в файл)");
    public static final FullCommandDesc REMOVE_AT = new FullCommandDesc("remove_at","Удаляет элемент, находящийся в заданной позиции коллекции (index)");
    public static final FullCommandDesc ADD_IF_MIN = new FullCommandDesc("add_if_min","Добавить новый элемент в коллекцию, если его значение (id) меньше, чем у наименьшего элемента этой коллекции");
    public static final FullCommandDesc SHUFFLE = new FullCommandDesc("shuffle", "Перемешать элементы коллекции в случайном порядке");
    public static final FullCommandDesc SUM_OF_NUMBER_OF_PARTICIPANTS = new FullCommandDesc("sum_of_number_of_participants","Выводит сумму значений поля numberOfParticipants для всех элементов в коллекции");
    public static final FullCommandDesc AVERAGE_OF_NUMBER_OF_PARTICIPANTS = new FullCommandDesc("average_of_number_of_participants","Выводит среднее значение поля numberOfParticipants для всех элементов коллекции");
    public static final FullCommandDesc PRINT_FIELD_DESCENDING_FRONT_MAN = new FullCommandDesc("print_field_descending_front_man","Выводит значение поля frontMan всех элементов в порядке убывания");

    public static class FullCommandDesc {
        public String name;
        public String desc;

        public FullCommandDesc(String name, String desc){
            this.desc = desc;
            this.name = name;
        }
    }

    public static Vector<FullCommandDesc> getAllCommands (){
        var c = new Vector<FullCommandDesc>();
        c.add(HELP);
        c.add(INFO);
        c.add(SHOW);
        c.add(ADD);
        c.add(UPDATE);
        c.add(REMOVE_BY_ID);
        c.add(CLEAR);
        c.add(SAVE);
        c.add(EXECUTE_SCRIPT);
        c.add(EXIT);
        c.add(REMOVE_AT);
        c.add(ADD_IF_MIN);
        c.add(SHUFFLE);
        c.add(SUM_OF_NUMBER_OF_PARTICIPANTS);
        c.add(AVERAGE_OF_NUMBER_OF_PARTICIPANTS);
        c.add(PRINT_FIELD_DESCENDING_FRONT_MAN);
        return c;
    }
}
