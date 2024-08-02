package likelion.Dotox.hobby.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.Dotox.friendlist.entity.Account;
import likelion.Dotox.hobby.dto.HobbyDto;
import likelion.Dotox.hobby.service.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Hobby", description = "취미 정보를 관리")
public class HobbyController {

    private final HobbyService hobbyService;

    @PostMapping("/{id}/userinfo")
    @Operation(
            summary = "사용자 정보 업데이트",
            description = "제공된 ID와 HobbyDto를 바탕으로 사용자의 취미 정보를 업데이트합니다.",
            responses = {
                    @ApiResponse(description = "사용자 정보가 성공적으로 업데이트됨", responseCode = "200"),
                    @ApiResponse(description = "사용자를 찾을 수 없음", responseCode = "404"),
                    @ApiResponse(description = "잘못된 입력", responseCode = "400")
            }
    )
    public Account updateInfo(
            @Parameter(description = "업데이트할 사용자의 ID") @PathVariable("id") String id,
            @Parameter(description = "업데이트할 취미 정보") @RequestBody HobbyDto hobbyDto
    ) {
        Account account = hobbyService.updateInfo(id, hobbyDto);
        System.out.println(account.getHobby());
        return account;
    }

}
