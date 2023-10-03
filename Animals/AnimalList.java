import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AnimalList <AnimalInterface>{
    private List <AnimalInterface> animals = new ArrayList<>();

    /**
     * Добавить животное в список
     * @param animal
     */
    public void addAnimal(AnimalInterface animal){
        animals.add(animal);
    }

    /**
     * Удалить животное из списка
     * @param animal
     * @return
     */
    public boolean removeAnimal(AnimalInterface animal){
        return animals.remove(animal);
    }

    /**
     * Получить список животных
     * @return
     */
    public List<AnimalInterface> getAnimals(){
        return animals;
    }

    /**
     * Получить список котов
     * @return
     */
    public List<AnimalInterface> getCats(){
        return animals.stream().filter(x -> x instanceof Cat).toList();
    }

    /**
     * Получить список собак
     * @return
     */
    public List<AnimalInterface> getDogs(){
        return animals.stream().filter(x -> x instanceof Dog).toList();
    }

    /**
     * Получить список хомяков
     * @return
     */
    public List<AnimalInterface> getHamsters(){
        return animals.stream().filter(x -> x instanceof Hamster).toList();
    }

    public Cat findCat(String name){
        List<Cat> cats = (List<Cat>) this.getCats();
        Cat cat = null;

        try{
            cat = cats.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex){
            cat = null;
        }
        return cat;
    }

    public Dog findDog(String name){
        List<Dog> dogs = (List<Dog>) this.getDogs();
        Dog dog = null;

        try{
            dog = dogs.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex){
            dog = null;
        }
        return dog;
    }

    public Hamster findHamster(String name){
        List<Hamster> hamsters = (List<Hamster>) this.getHamsters();
        Hamster hamster = null;

        try{
            hamster = hamsters.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex){
            hamster = null;
        }
        return hamster;
    }
}
