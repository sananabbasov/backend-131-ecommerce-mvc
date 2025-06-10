package az.edu.itbrains.fruitables.repositories;

import az.edu.itbrains.fruitables.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
