package repository.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import repository.DeveloperRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private final String REPOSITORY_PATH = "src\\main\\resources\\developers.txt";
    private Long currentMaxId = 0L;

    private void generateCurrentMaxId() {
        if(!getAll().isEmpty()) {
            currentMaxId = getAll().get(getAll().size() - 1).getId() + 1;
        }
    }

    @Override
    public Developer create(Developer developer) {
        List<Developer> developerList = new ArrayList<>(getAll());
        generateCurrentMaxId();
        developer.setId(currentMaxId);
        developerList.add(developer);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                REPOSITORY_PATH))) {
            new Gson().toJson(developer, bw);
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return developer;
    }

    @Override
    public Developer read(Long aLong) {
        return getAll().stream()
                .filter(n -> n.getId() == aLong)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Developer update(Long aLong, Developer developer) {
        List<Developer> developerList = new ArrayList<>(getAll());

        if (developerList.stream().anyMatch(n -> n.getId() == aLong)) {
            developerList.stream()
                    .filter(n -> n.getId() == aLong)
                    .map(n -> new Developer(
                            aLong,
                            developer.getName(),
                            developer.getAccount(),
                            developer.getSkills()));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                    REPOSITORY_PATH))) {
                new Gson().toJson(developerList, bw);
            } catch (IOException e) {
                System.out.println("IOException");
            }
            return developer;
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long aLong) {
        List<Developer> developerList = new ArrayList<>(getAll());
        int index = IntStream.range(0, developerList.size())
                .filter(n -> developerList.get(n).getId() == aLong)
                .findFirst()
                .orElse(-1);
        developerList.remove(index);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                REPOSITORY_PATH))) {
            new Gson().toJson(developerList, bw);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = null;

        try (BufferedReader br = new BufferedReader(new FileReader(
                REPOSITORY_PATH))) {
            developerList = new Gson().fromJson(
                    br, new TypeToken<List<Developer>>(){}.getType());
            if (developerList == null) {
                developerList = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return developerList;
    }
}
