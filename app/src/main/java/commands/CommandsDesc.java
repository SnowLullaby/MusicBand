package commands;

import java.util.Vector;

public class CommandsDesc {
    public static final FullCommandDesc HELP = new FullCommandDesc("help", "display commands info");
    public static final FullCommandDesc INFO = new FullCommandDesc("info", "display information about collection (tip, creation date and etc.)");
    public static final FullCommandDesc SHOW = new FullCommandDesc("show", "display all elements from collection");
    public static final FullCommandDesc ADD = new FullCommandDesc("add","add new element to collection");
    public static final FullCommandDesc UPDATE = new FullCommandDesc("update","update element with same id");
    public static final FullCommandDesc REMOVE_BY_ID = new FullCommandDesc("remove_by_id","remove element by id");
    public static final FullCommandDesc CLEAR = new FullCommandDesc("clear","clear collection");
    public static final FullCommandDesc SAVE = new FullCommandDesc("save","save collection into file");
    public static final FullCommandDesc EXECUTE_SCRIPT = new FullCommandDesc("execute_script","Execute script from file. Script contain commands, same as in interactive mode");
    public static final FullCommandDesc EXIT = new FullCommandDesc("exit","exiting program (without saving)");
    public static final FullCommandDesc REMOVE_AT = new FullCommandDesc("remove_at","remove element with this index");
    public static final FullCommandDesc ADD_IF_MIN = new FullCommandDesc("add_if_min","add new element if it id less than minimum element id in collection");
    public static final FullCommandDesc SHUFFLE = new FullCommandDesc("shuffle", "shuffle all elements from collection randomly");
    public static final FullCommandDesc SUM_OF_NUMBER_OF_PARTICIPANTS = new FullCommandDesc("sum_of_number_of_participants","display sum of number of participants for all music bands");
    public static final FullCommandDesc AVERAGE_OF_NUMBER_OF_PARTICIPANTS = new FullCommandDesc("average_of_number_of_participants","display average of number of participants for all music bands");
    public static final FullCommandDesc PRINT_FIELD_DESCENDING_FRONT_MAN = new FullCommandDesc("print_field_descending_front_man","display all front men and their fields");

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
