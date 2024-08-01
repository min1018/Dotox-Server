package likelion.Dotox.hobby.controller;


import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.hobby.dto.HobbyDto;
import likelion.Dotox.hobby.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HobbyController {

    private final HobbyService hobbyService;

    @PostMapping("/{id}/userinfo")
    public Account updateInfo(@PathVariable("id") String id, @RequestBody HobbyDto hobbyDto) {
        Account account = hobbyService.updateInfo(id, hobbyDto);
        System.out.println(account.getHobby());
        return account;
    }

}
