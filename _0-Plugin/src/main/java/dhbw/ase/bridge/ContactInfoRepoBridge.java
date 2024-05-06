package dhbw.ase.bridge;

import dhbw.domain.aggregate.ContactInfo;
import dhbw.domain.ports.ContactInfoRepository;
import dhbw.ase.repository.ContactInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactInfoRepoBridge implements ContactInfoRepository {

    private final ContactInfoJpaRepository contactInfoJpaRepository;

    @Autowired
    public ContactInfoRepoBridge(ContactInfoJpaRepository contactInfoJpaRepository) {
        this.contactInfoJpaRepository = contactInfoJpaRepository;
    }

    @Override
    public Optional<ContactInfo> findById(Long id) {
        return contactInfoJpaRepository.findById(id);
    }

    @Override
    public List<ContactInfo> findAll() {
        return contactInfoJpaRepository.findAll();
    }

    @Override
    public ContactInfo save(ContactInfo contactInfo) {
        return contactInfoJpaRepository.save(contactInfo);
    }

    @Override
    public void deleteById(Long id) {
        contactInfoJpaRepository.deleteById(id);
    }

    @Override
    public ContactInfo update(Long id, String phoneNumber, String address, String email) {
        contactInfoJpaRepository.updateContactInfo(id, phoneNumber, address, email);
        return contactInfoJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Updated ContactInfo not found with id: " + id));
    }
}
