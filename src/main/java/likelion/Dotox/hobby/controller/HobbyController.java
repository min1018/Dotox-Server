package likelion.Dotox.hobby.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Update User Information",
            description = "Updates the user's information with the given hobby details.",
            tags = { "Hobby Management" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/{id}/userinfo")
    public Account updateInfo(
            @Parameter(description = "ID of the user to update", required = true)
            @PathVariable("id") String id,
            @RequestBody
            @Parameter(description = "Hobby details to update")
            HobbyDto hobbyDto )
    {
        Account account = hobbyService.updateInfo(id, hobbyDto);
        System.out.println(account.getHobby());
        return account;
    }

}
