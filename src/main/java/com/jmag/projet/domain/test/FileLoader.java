package com.jmag.projet.domain.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader {

    private static String PRODUIT_SOURCE_PATH = "json/planerTree.json";

    public static PlanerTree getPlanerTree() throws URISyntaxException, IOException {
        LocalObjectMapper mapper = new LocalObjectMapper(new ObjectMapper());

        var jsonProduit = readFile(PRODUIT_SOURCE_PATH);
        PlanerTree planerTree = mapper.convertObject(jsonProduit, PlanerTree.class);

        return planerTree;
    }

    private static String readFile(String path) throws URISyntaxException, IOException {
        return Files.readString(Paths.get(ClassLoader.getSystemResource(path).toURI()));
    }
}