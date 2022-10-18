package com.gtauber.serverapp.resource;

import com.gtauber.serverapp.enumeration.Status;
import com.gtauber.serverapp.model.Response;
import com.gtauber.serverapp.model.Server;
import com.gtauber.serverapp.service.impl.ServerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static com.gtauber.serverapp.util.Constants.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/v1/server")
public class ServerResource {
    private final ServerServiceImpl serverServiceImpl;

    public ServerResource(ServerServiceImpl serverServiceImpl) {
        this.serverServiceImpl = serverServiceImpl;
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("servers", serverServiceImpl.listServers(30)))
                        .message("Servers retrieved successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverServiceImpl.pingServer(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("servers", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Server is up" : "Server is down")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        serverServiceImpl.createServer(server);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of(SERVERS, server))
                        .message("Server saved successfully")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of(SERVER, serverServiceImpl.getServer(id)))
                        .message("Server retrieved successfully TESTE")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Deleted", serverServiceImpl.deleteServer(id)))
                        .message("Server Deleted successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }
    @GetMapping (path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImg(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Development/Projects/serverApp/serverApp" +
                "/src/main/resources/assets/" + fileName));
    }
}
