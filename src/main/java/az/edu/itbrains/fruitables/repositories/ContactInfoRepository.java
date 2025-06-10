package az.edu.itbrains.fruitables.repositories;

import az.edu.itbrains.fruitables.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo,Long> {
}
