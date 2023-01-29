package com.jmag.projet.application;

import com.jmag.projet.domain.test.FileLoader;
import com.jmag.projet.domain.test.PlanerTree;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class PlanerTreeService {

    @SneakyThrows
    public PlanerTree addChild (PlanerTree child) {

        PlanerTree initialPlanerTree = FileLoader.getPlanerTree();
        initialPlanerTree.addChild(child);
        return initialPlanerTree;
    }

    @SneakyThrows
    public PlanerTree findChild(PlanerTree child) {

        PlanerTree initialPlanerTree = FileLoader.getPlanerTree();
        return initialPlanerTree.findPlanerTree(child);
    }
}
