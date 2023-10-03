import java.util.*;
import java.util.logging.Logger;

public class Controller {

    private final AnimalList<Object> animalList = new AnimalList<>();
    private final MenuActions menuActions = new MenuActions();

    /**
     * Главное меню
     */
    private final Map<String, String> menuMain = new HashMap<String, String >() {{
        put("1", "Добавить животное");
        put("2", "Добавить команду");
        put("3", "Отобразить список");
        put("4", "Показать команды");
        put("5", "Показать количество животных");
        put("0", "Выход");
    }};

    /**
     * Подменю
     */
    private final Map<String,String> menuAnimal = new HashMap<>() {{
        put("1", "Кот");
        put("2", "Собака");
        put("3", "Хомяк");
        put("0", "Отмена");
    }};

    private final Map<String,String> nemuYesNo = new HashMap<>() {{
        put("1", "Да");
        put("0", "Нет");
    }};

    private enum ANIMALS {CAT, DOG, HAMSTER};

    public void Run() throws Exception{
        String menu;
        do {
            menu = getOperation();

            switch (menu){
                case "11" -> addAnimal(ANIMALS.CAT);
                case "12" -> addAnimal(ANIMALS.DOG);
                case "13" -> addAnimal(ANIMALS.HAMSTER);
                case "21" -> addCommand(ANIMALS.CAT);
                case "22" -> addCommand(ANIMALS.DOG);
                case "23" -> addCommand(ANIMALS.HAMSTER);
                case "31" -> showAnimals(ANIMALS.CAT);
                case "32" -> showAnimals(ANIMALS.DOG);
                case "33" -> showAnimals(ANIMALS.HAMSTER);
                case "41" -> showCommands(ANIMALS.CAT);
                case "42" -> showCommands(ANIMALS.DOG);
                case "43" -> showCommands(ANIMALS.HAMSTER);
                case "5" -> showCountAnimals();
            }
        } while (!(menu.isEmpty() || menu.equals("0")));
    }

    private void showCountAnimals() throws Exception{
        try (AnimalCounter counter = new AnimalCounter()){
            Logger.getAnonymousLogger().info(counter.getCount().toString());
        }
    }

    /**
     * Отобразить список команд
     * @param animals
     */
    private void showCommands(ANIMALS animals){
        String name = menuActions.getString("Имя животного: ");
        Object o = null;
        switch (animals){
            case CAT -> o = animalList.findCat(name);
            case DOG -> o = animalList.findDog(name);
            case HAMSTER -> o = animalList.findHamster(name);
        }
        if (o == null){
            Logger.getAnonymousLogger().info("Животное не найдено!");
            return;
        }

        List<String> commands = null;
        switch (animals){
            case CAT -> commands = ((Cat)o).getCommandList();
            case DOG -> commands = ((Dog)o).getCommandList();
            case HAMSTER -> commands = ((Hamster)o).getCommandList();
        }
        StringBuilder strCommands = new StringBuilder();
        for (String c : commands){
            strCommands.append(c).append(", ");
        }
        Logger.getAnonymousLogger().info(strCommands.toString());
    }

    /**
     * Отображение списка животных
     * @param animals
     */
    private void showAnimals(ANIMALS animals){
        List<Object> animal = null;
        switch (animals){
            case CAT -> animal = animalList.getCats();
            case DOG -> animal = animalList.getDogs();
            case HAMSTER -> animal = animalList.getHamsters();
        }
        Logger logger = Logger.getAnonymousLogger();
        for (Object o : animal){
            logger.info(o.toString());
        }
    }

    /**
     * Добавление команды
     * @param animals
     */
    private void addCommand(ANIMALS animals){
        String name = menuActions.getString("Имя животного: ");
        Object objAnimal = null;
        switch (animals){
            case CAT -> objAnimal = animalList.findCat(name);
            case DOG -> objAnimal = animalList.findDog(name);
            case HAMSTER -> objAnimal = animalList.findHamster(name);
        }
        if (objAnimal == null){
            Logger.getAnonymousLogger().info("Такое животное не найдено!");
        }
        else {
            String command = menuActions.getString("Новая команда: ");

            switch (animals){
                case CAT -> ((Cat)objAnimal).addCommand(command);
                case DOG -> ((Dog)objAnimal).addCommand(command);
                case HAMSTER -> ((Hamster)objAnimal).addCommand(command);
            }
        }
    }

    /**
     * Добавление нового животного
     * @param animals
     * @throws Exception
     */
    private void addAnimal(ANIMALS animals) throws Exception{
        try (AnimalCounter counter = new AnimalCounter()){
            counter.add();
        }

        String name = menuActions.getString("Имя животного: ");
        String date = menuActions.getString("Дата рождения: ");

        List<String> commands = new ArrayList<>();
        System.out.println("Добавить команды?");
        String menu = menuActions.menuShow(nemuYesNo);
        while (menu.equals("1")){
            String command = menuActions.getString("Команда: ");
            commands.add(command);
            System.out.println("Продолжить?");
            menu = menuActions.menuShow(nemuYesNo);
        }

        switch (animals){
            case CAT -> animalList.addAnimal(new Cat(name, date, commands));
            case DOG -> animalList.addAnimal(new Dog(name, date, commands));
            case HAMSTER -> animalList.addAnimal(new Hamster(name, date, commands));
        }
    }

    /**
     * Выбор операции в меню операции
     * @return
     */
    private String getOperation(){
        String menu = menuActions.menuShow(menuMain);
        if (!menu.isEmpty() && !menu.equals("0") && !menu.equals("5")){
            menu += menuActions.menuShow(menuAnimal);
        }
        return menu;
    }
}
