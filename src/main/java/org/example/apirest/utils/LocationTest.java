package org.example.apirest.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationTest {
    private Double lat;
    private Double lon;
    private Double ele;
    private LocalDateTime dateTime;
}
