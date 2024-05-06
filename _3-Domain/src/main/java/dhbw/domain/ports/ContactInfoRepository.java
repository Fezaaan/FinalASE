package dhbw.domain.ports;

import dhbw.domain.aggregate.ContactInfo;
import java.util.List;
import java.util.Optional;

public interface ContactInfoRepository {
    Optional<ContactInfo> findById(Long id);
    List<ContactInfo> findAll();
    ContactInfo save(ContactInfo contactInfo);
    void deleteById(Long id);
    ContactInfo update(Long id, String phoneNumber, String address, String email);
}
