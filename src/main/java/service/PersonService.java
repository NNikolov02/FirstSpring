package service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.firstspring.firstspring.Web.error.NotFoundObjectException;
import com.firstspring.firstspring.Web.model.Person;
import com.firstspring.firstspring.Web.model.Photo;
import com.firstspring.firstspring.Web.repository.PersonPagingRepository;
import com.firstspring.firstspring.Web.repository.PersonRepository;
import com.firstspring.firstspring.Web.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    @Autowired
    private PersonRepository repo;

    @Autowired
    private PersonPagingRepository pagingRepo;

    @Autowired
    private PhotoRepository photoRepo;

    public Person save(Person person) {
        return repo.save(person);
    }

    public Page<Person> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Person findById(String personId) {
        return repo.findById(UUID.fromString(personId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", Person.class.getName(), personId);
        });
    }

    public void deleteById(String personId) {
        repo.deleteById(UUID.fromString(personId));
    }

    public Set<UUID> getAllPersonPhotoIds(String personId) {
        Person person = repo.findById(UUID.fromString(personId)).get();

        Set<UUID> allPersonPhotoIds = new HashSet<>();
        for (Photo photo : person.getPhotos()) {
            allPersonPhotoIds.add(photo.getId());
        }

        return allPersonPhotoIds;
    }

    public Set<UUID> setPersonPhotos(String personId, Set<UUID> personPhotoIds) {
        Person person = repo.findById(UUID.fromString(personId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Person Not Found", Person.class.getName(), personId);
        });

        List<Photo> allPersonPhotos =
                (List<Photo>) photoRepo.findAllById(personPhotoIds);

        person.setPhotos(new HashSet<>(allPersonPhotos));
        Person savedPerson = repo.save(person);

        Set<UUID> allPersonPhotoIds = new HashSet<>();
        for (Photo photo : savedPerson.getPhotos()) {
            allPersonPhotoIds.add(photo.getId());
        }

        return allPersonPhotoIds;
    }
}