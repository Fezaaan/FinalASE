package dhbw.ase.repository;

import dhbw.domain.aggregate.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactInfoJpaRepository extends JpaRepository<ContactInfo, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE ContactInfo c SET c.phoneNumber = :phoneNumber, c.address = :address, c.email = :email WHERE c.id = :id")
    int updateContactInfo(Long id, String phoneNumber, String address, String email);
}
