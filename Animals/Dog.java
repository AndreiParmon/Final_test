import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dog extends AnimalInterface {

    private String name;
    private String dateBirth;
    private List<String> commands;



    public Dog() {
        this("","",new ArrayList<>());
    }

    public Dog(String name, String dateBirth, List<String> commands) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.commands = commands;
    }

    /**
     * Получить имя животного
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Установить имя животного
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Пополнение списка команд животного
     * @param newCommand
     */
    @Override
    public void addCommand(String newCommand) {
        if (commands.stream().filter(x -> x.equals(newCommand)).findFirst().isEmpty()){
            return;
        }
        commands.add(newCommand);
    }

    /**
     * Удаление команды
     * @param command
     */
    @Override
    public void removeCommand(String command) {
        commands.remove(command);
    }

    /**
     * Получить список команд
     * @return
     */
    @Override
    public List<String> getCommandList() {
        return commands;
    }

    /**
     * Количество команд животного
     * @return
     */
    @Override
    public int getCommandCount() {
        return commands.size();
    }

    /**
     * Получить дату рождения
     * @return
     */
    @Override
    public String getDateBirth() {
        return dateBirth;
    }

    /**
     * Установить дату рождения
     * @param date
     */
    @Override
    public void setDateBirth(String date) {
        this.dateBirth = date;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Собака ").append(name).append(" ").append(" - ").append(dateBirth);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
}
