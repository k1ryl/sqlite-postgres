package vkir.sqlitepostgres.migration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vkir.sqlitepostgres.migration.service.DataMigrationService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DataMigrationController {

    private final DataMigrationService dataMigrationService;

    @PostMapping(path = "/migrate")
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        if (files.length != 0) {
            String uuid = UUID.randomUUID().toString();
            try {
                for (MultipartFile file : files) {
                    dataMigrationService.migrate(file, uuid);
                }
                return ResponseEntity.ok("Successfully migrated.");
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Error migrating files: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("No files to migrate.");
        }
    }
}