package likelion.Dotox.hobby.service;


import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.hobby.dto.HobbyDto;
import likelion.Dotox.hobby.repository.HobbyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HobbyService {
    private final HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    @Transactional
    public Account updateInfo(String id, HobbyDto hobbyDto) {
        Account userAccount = hobbyRepository.findByAccountId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + id));

        userAccount.setHobby(hobbyDto.getHobby());
        userAccount.setGender(hobbyDto.getGender());
        userAccount.setAge(hobbyDto.getAge());

        hobbyRepository.save(userAccount);
        return userAccount;

    }

}
