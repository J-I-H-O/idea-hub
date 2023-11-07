package com.jbnu.ideahub.star.presentation;

import com.jbnu.ideahub.star.dto.request.StarRequest;
import com.jbnu.ideahub.star.service.StarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stars")
public class StarController {

    private final StarService starService;

    @PostMapping
    public ResponseEntity<Void> createStar(
            @RequestBody @Valid final StarRequest starRequest
    ) {
        starService.save(starRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStar(
            @RequestBody @Valid final StarRequest starRequest
    ) {
        starService.delete(starRequest);
        return ResponseEntity.noContent().build();
    }
}
