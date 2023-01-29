package com.jmag.projet.domain.test;


import com.jmag.projet.domain.exceptions.PlanerTreeBadRequestException;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

public class PlanerTree {

    private int id;
    private String name;
    private Set<PlanerTree> childs = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlanerTree> getChilds() {
        return childs;
    }

    public void setChilds(Set<PlanerTree> childs) {
        this.childs = childs;
    }

    public PlanerTree () {
    }

    public void addChild(PlanerTree child) {

        validatePlanerTreeExists(child);
        this.childs.add(child);
    }

    private void validatePlanerTreeExists(PlanerTree planerTree) {

        if (isEqualsPlanerTree(this, planerTree) ||
                (nonNull(this.findPlanerTree(planerTree)) && nonEmptyTree(this.findPlanerTree(planerTree)))) {

            throw new PlanerTreeBadRequestException(
                    String.format("The planerTree with id %s and name %s exists",
                            planerTree.getId(),
                            planerTree.getName()));
        }
    }

    public PlanerTree findPlanerTree(PlanerTree planerTree) {
        final PlanerTree tree = new PlanerTree();
        browsePlanerTree(planerTree, tree, false);
        return tree;
    }

    public void browsePlanerTree(PlanerTree rootTree,
                                 PlanerTree paramTree,
                                 Boolean found) {
        if (found) {
            paramTree.setId(rootTree.getId());
            paramTree.setName(rootTree.getName());
            paramTree.setChilds(rootTree.getChilds());
        } else {
            for (PlanerTree child : childs) {
                child.browsePlanerTree(rootTree,
                        paramTree,
                        isEqualsPlanerTree(child, rootTree));
            }
        }
    }

    private boolean nonEmptyTree(PlanerTree planerTree) {

        return planerTree.getId() != 0 && nonNull(planerTree.getName());
    }

    private boolean isEqualsPlanerTree(PlanerTree planerTree1, PlanerTree planerTree2) {
        return planerTree1.getId() == planerTree2.getId() &&
                planerTree1.getName().equals(planerTree2.getName());
    }
}
